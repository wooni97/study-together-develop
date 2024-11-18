package dev.flab.studytogether.domain.chat.infrastructure;

import dev.flab.studytogether.domain.chat.entity.ChatParticipant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatParticipantJpaRepository extends JpaRepository<ChatParticipant, Long> {
    Optional<ChatParticipant> findByChatIdAndMemberId(Long chatId, Long memberId);
}
