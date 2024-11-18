package dev.flab.studytogether.websocket.handler;

import dev.flab.studytogether.domain.chat.exception.ChatNotFoundException;
import dev.flab.studytogether.domain.chat.exception.MemberNotFoundInChatException;
import dev.flab.studytogether.websocket.response.WebSocketErrorResponse;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Map;

@ControllerAdvice
public class WebSocketExceptionHandler {

    @MessageExceptionHandler(ChatNotFoundException.class)
    @SendToUser("/queue/errors")
    public WebSocketErrorResponse handleChatNotFoundException(ChatNotFoundException ex) {
        return WebSocketErrorResponse.sendErrorMessage(
                404,
                ex.getMessage(),
                Map.of("Chat Id", ex.getChatId()));
    }

    @MessageExceptionHandler(MemberNotFoundInChatException.class)
    @SendToUser("/queue/errors")
    public WebSocketErrorResponse handleMemberNotFoundInChatException(MemberNotFoundInChatException ex) {
        return WebSocketErrorResponse.sendErrorMessage(
                404,
                ex.getMessage(),
                Map.of("Chat Id", ex.getChatId(),
                        "Member Id", ex.getMemberId()));
    }
}
