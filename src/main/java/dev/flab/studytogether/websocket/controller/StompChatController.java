package dev.flab.studytogether.websocket.controller;

import dev.flab.studytogether.core.domain.chat.entity.ChatMessage;
import dev.flab.studytogether.core.domain.chat.service.ChatMessageHandlerService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
public class StompChatController {

    private final ChatMessageHandlerService handlerService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public StompChatController(ChatMessageHandlerService handlerService, SimpMessagingTemplate simpMessagingTemplate) {
        this.handlerService = handlerService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/chat")
    public void sendChatMessage(@Payload @Valid ChatMessage chatMessage) {
        ChatMessage handledMessage = handlerService.handleMessage(chatMessage);
        simpMessagingTemplate.convertAndSend(
                "/subscribe/chat/chatId/" + chatMessage.getChatId(),
                handledMessage);
    }
}
