package dev.flab.studytogether.domain.studygroup.controller.stomp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.flab.studytogether.domain.studygroup.service.StudyGroupExcessParticipantHandlerService;

import dev.flab.studytogether.websocket.enums.PayloadType;
import dev.flab.studytogether.websocket.ServerMessagePayload;
import lombok.RequiredArgsConstructor;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class StompStudyGroupController {

    private final StudyGroupExcessParticipantHandlerService excessParticipantHandlerService;

    @MessageMapping("/studyGroup/attempt/join")
    @SendToUser("/queue/group")
    public ServerMessagePayload attemptJoinStudyGroup(@Payload String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode messageNode = objectMapper.readTree(message);
        return handleJoinAttemptAndReceiveMessage(messageNode);
    }

    private ServerMessagePayload handleJoinAttemptAndReceiveMessage(JsonNode messageNode) {
        Long groupId = messageNode.get("groupId").asLong();
        Long participantId = messageNode.get("participantId").asLong();

        boolean isDeactivated = excessParticipantHandlerService
                        .isParticipantDeactivatedIfOverCapacity(groupId, participantId);

        return isDeactivated
                ? new ServerMessagePayload(PayloadType.JOIN_LIMIT_REACHED, "정원 초과로 입장 실패")
                : new ServerMessagePayload(PayloadType.JOIN_SUCCESS, "입장 완료");
    }
}
