package dev.flab.studytogether.domain.chat.infrastructure;

import dev.flab.studytogether.domain.chat.entity.ChatMessage;
import dev.flab.studytogether.domain.chat.repository.ChatMessageRepository;
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
