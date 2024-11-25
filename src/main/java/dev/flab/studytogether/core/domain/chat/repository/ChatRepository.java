package dev.flab.studytogether.core.domain.chat.repository;

import dev.flab.studytogether.core.domain.chat.entity.Chat;

import java.util.Optional;

public interface ChatRepository {
    Chat save(Chat chat);
    Optional<Chat> findById(Long id);
}
