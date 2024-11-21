package dev.flab.studytogether.domain.chat.repository;

import dev.flab.studytogether.domain.chat.entity.Chat;

import java.util.Optional;

public interface ChatRepository {
    Chat save(Chat chat);
    Optional<Chat> findById(Long id);
}
