package dev.flab.studytogether.domain.studygroup.controller.stomp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.flab.studytogether.domain.studygroup.service.StudyGroupExcessParticipantHandlerService;
import dev.flab.studytogether.websocket.enums.StompMessageType;
import dev.flab.studytogether.websocket.enums.PayloadType;
import dev.flab.studytogether.websocket.ServerMessagePayload;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class StompStudyGroupController {

    private final SimpMessagingTemplate messagingTemplate;
    private final StudyGroupExcessParticipantHandlerService excessParticipantHandlerService;

    @MessageMapping("/study-group")
    public void handleMessage(@Payload String message, Principal principal) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode messageNode = objectMapper.readTree(message);

        StompMessageType messageType = StompMessageType.findByMessageType(messageNode.get("type").asText());

        if(messageType.equals(StompMessageType.JOIN)) {
            handleJoin(messageNode, principal);
            return;
        }

        if(messageType.equals(StompMessageType.CHAT)) {
            // 구현 예정
            throw new UnsupportedOperationException();
        }
    }

    private void handleJoin(JsonNode messageNode, Principal principal) {
        Long groupId = messageNode.get("groupId").asLong();

        List<Long> latestParticipantIds =
                excessParticipantHandlerService.handleExcessParticipants(groupId);

        if(latestParticipantIds.contains(messageNode.get("participantId").asLong())) {
            messagingTemplate.convertAndSendToUser(
                    principal.getName(),
                    "/queue/group" + groupId,
                    new ServerMessagePayload(PayloadType.JOIN_LIMIT_REACHED, "정원 초과로 입장 실패")
                    );

            return;
        }

        messagingTemplate.convertAndSendToUser(
                principal.getName(),
                "/queue/group" + groupId,
                new ServerMessagePayload(PayloadType.JOIN_SUCCESS, "입장 완료")
        );
    }
}
