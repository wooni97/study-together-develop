package dev.flab.studytogether.domain.chat.infrastructure;

import dev.flab.studytogether.domain.chat.entity.Chat;
import dev.flab.studytogether.domain.chat.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ChatRepositoryImpl implements ChatRepository {
    @Autowired
    private ChatJpaRepository chatJpaRepository;

    @Override
    public Chat save(Chat chat) {
        return chatJpaRepository.save(chat);
    }

    @Override
    public Optional<Chat> findById(Long id) {
        return chatJpaRepository.findById(id);
    }
}
