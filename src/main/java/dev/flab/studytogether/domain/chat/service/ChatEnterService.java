package dev.flab.studytogether.domain.chat.service;

import dev.flab.studytogether.domain.chat.entity.ChatParticipant;
import dev.flab.studytogether.domain.chat.exception.MemberAlreadyExistsInChatException;
import dev.flab.studytogether.domain.chat.repository.ChatParticipantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ChatEnterService {

    private final ChatParticipantRepository chatParticipantRepository;

    @Transactional
    public void chatEnter(Long chatId, Long memberId) {
        if(chatParticipantRepository.findByChatIdAndMemberId(chatId, memberId).isPresent())
            throw new MemberAlreadyExistsInChatException(chatId, memberId);

        chatParticipantRepository.save(new ChatParticipant(chatId, memberId));
    }
}
