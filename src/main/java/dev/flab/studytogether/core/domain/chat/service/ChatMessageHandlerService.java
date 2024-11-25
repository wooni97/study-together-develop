package dev.flab.studytogether.core.domain.chat.service;

import dev.flab.studytogether.core.domain.chat.entity.ChatMessage;
import dev.flab.studytogether.core.domain.chat.repository.ChatMessageRepository;
import org.springframework.stereotype.Service;

@Service
public class ChatMessageHandlerService {

    private final ChatMessageRepository chatMessageRepository;

    public ChatMessageHandlerService(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    public ChatMessage handleMessage(ChatMessage chatMessage) {
        return chatMessageRepository.save(chatMessage);
    }
}
