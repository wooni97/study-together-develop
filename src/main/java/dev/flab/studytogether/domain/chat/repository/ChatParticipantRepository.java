package dev.flab.studytogether.domain.chat.repository;

import dev.flab.studytogether.domain.chat.entity.ChatParticipant;

import java.util.Optional;

public interface ChatParticipantRepository {
    ChatParticipant save(ChatParticipant chatParticipant);
    Optional<ChatParticipant> findByChatIdAndMemberId(Long chatId, Long memberId);
    void deleteByChatIdAndMemberId(Long chatId, Long memberId);
}
