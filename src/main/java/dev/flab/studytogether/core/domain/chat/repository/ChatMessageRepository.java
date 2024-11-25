package dev.flab.studytogether.core.domain.chat.repository;

import dev.flab.studytogether.core.domain.chat.entity.ChatMessage;

public interface ChatMessageRepository {
    ChatMessage save(ChatMessage chatMessage);
}
