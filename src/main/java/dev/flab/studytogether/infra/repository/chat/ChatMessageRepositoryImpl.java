package dev.flab.studytogether.infra.repository.chat;

import dev.flab.studytogether.core.domain.chat.entity.ChatMessage;
import dev.flab.studytogether.core.domain.chat.repository.ChatMessageRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ChatMessageRepositoryImpl implements ChatMessageRepository {

    private final ChatMessageJpaRepository chatMessageJpaRepository;

    public ChatMessageRepositoryImpl(ChatMessageJpaRepository chatMessageJpaRepository) {
        this.chatMessageJpaRepository = chatMessageJpaRepository;
    }

    @Override
    public ChatMessage save(ChatMessage chatMessage) {
        return chatMessageJpaRepository.save(chatMessage);
    }
}
