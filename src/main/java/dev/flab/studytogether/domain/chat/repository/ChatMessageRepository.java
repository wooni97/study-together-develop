package dev.flab.studytogether.domain.chat.repository;

import dev.flab.studytogether.domain.chat.entity.ChatMessage;

public interface ChatMessageRepository {
    ChatMessage save(ChatMessage chatMessage);
}
