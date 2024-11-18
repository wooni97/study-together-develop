package dev.flab.studytogether.domain.chat.infrastructure;

import dev.flab.studytogether.domain.chat.entity.ChatParticipant;
import dev.flab.studytogether.domain.chat.repository.ChatParticipantRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ChatParticipantRepositoryImpl implements ChatParticipantRepository {

    private final ChatParticipantJpaRepository chatParticipantJpaRepository;

    public ChatParticipantRepositoryImpl(ChatParticipantJpaRepository chatParticipantJpaRepository) {
        this.chatParticipantJpaRepository = chatParticipantJpaRepository;
    }

    @Override
    public ChatParticipant save(ChatParticipant chatParticipant) {
        return chatParticipantJpaRepository.save(chatParticipant);
    }

    @Override
    public Optional<ChatParticipant> findByChatIdAndMemberId(Long chatId, Long memberId) {
        return chatParticipantJpaRepository.findByChatIdAndMemberId(chatId, memberId);
    }
}
