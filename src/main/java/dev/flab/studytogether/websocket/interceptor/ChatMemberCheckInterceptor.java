package dev.flab.studytogether.websocket.interceptor;

import dev.flab.studytogether.core.domain.chat.exception.ChatNotFoundException;
import dev.flab.studytogether.core.domain.chat.exception.MemberNotFoundInChatException;
import dev.flab.studytogether.core.domain.chat.service.ChatValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;


public class ChatMemberCheckInterceptor implements HandshakeInterceptor {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final ChatValidationService chatValidationService;

    public ChatMemberCheckInterceptor(ChatValidationService chatValidationService) {
        this.chatValidationService = chatValidationService;
    }


    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        URI uri  = request.getURI();
        Map<String, String> queryParams = UriComponentsBuilder.fromUri(uri)
                .build()
                .getQueryParams()
                .toSingleValueMap();

        Long chatID = Long.valueOf(queryParams.get("chatId"));
        Long memberId = Long.valueOf(queryParams.get("memberId"));

        try {
            chatValidationService.validate(chatID, memberId);
        } catch (ChatNotFoundException e) {
            log.error("error message : {}", e.getMessage());
            response.setStatusCode(HttpStatus.NOT_FOUND);
            return false;
        } catch (MemberNotFoundInChatException e) {
            log.error("error message : {}", e.getMessage());
            response.setStatusCode(HttpStatus.FORBIDDEN);
            return false;
        } catch (Exception e) {
            log.error("error type : {} error message : {}", e.getClass(), e.getMessage());
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        if(exception == null) {
            log.info("Handshake succeeded for URI : {}", request.getURI());
        }
    }
}
