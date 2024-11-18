package dev.flab.studytogether.domain.chat.service;

import dev.flab.studytogether.domain.chat.exception.ChatNotFoundException;
import dev.flab.studytogether.domain.chat.exception.MemberNotFoundInChatException;
import dev.flab.studytogether.domain.chat.repository.ChatParticipantRepository;
import dev.flab.studytogether.domain.chat.repository.ChatRepository;
import org.springframework.stereotype.Service;

@Service
public class ChatValidationService {

    private final ChatRepository chatRepository;

    private final ChatParticipantRepository chatParticipantRepository;

    public ChatValidationService(ChatRepository chatRepository,
                                 ChatParticipantRepository chatParticipantRepository) {
        this.chatRepository = chatRepository;
        this.chatParticipantRepository = chatParticipantRepository;
    }

    public void validate(Long chatId, Long memberId) {
        if(chatRepository.findById(chatId).isEmpty())
            throw new ChatNotFoundException(chatId);

        if(chatParticipantRepository.findByChatIdAndMemberId(
                chatId,
                memberId).isEmpty())
            throw new MemberNotFoundInChatException(chatId, memberId);
    }
}
