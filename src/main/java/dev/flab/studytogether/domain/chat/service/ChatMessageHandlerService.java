package dev.flab.studytogether.domain.chat.service;

import dev.flab.studytogether.domain.chat.entity.ChatMessage;
import dev.flab.studytogether.domain.chat.repository.ChatMessageRepository;
import org.springframework.stereotype.Service;

@Service
public class ChatMessageHandlerService {

    private final ChatMessageValidationService chatMessageValidationService;
    private final ChatMessageRepository chatMessageRepository;

    public ChatMessageHandlerService(ChatMessageValidationService chatMessageValidationService,
                                     ChatMessageRepository chatMessageRepository) {
        this.chatMessageValidationService = chatMessageValidationService;
        this.chatMessageRepository = chatMessageRepository;
    }

    public ChatMessage handleMessage(ChatMessage chatMessage) {
        chatMessageValidationService.validate(chatMessage);
        return chatMessageRepository.save(chatMessage);
    }
}
