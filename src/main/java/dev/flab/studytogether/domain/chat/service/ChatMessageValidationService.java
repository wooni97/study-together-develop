package dev.flab.studytogether.domain.chat.service;

import dev.flab.studytogether.domain.chat.entity.ChatMessage;
import dev.flab.studytogether.domain.chat.exception.ChatNotFoundException;
import dev.flab.studytogether.domain.chat.exception.MemberNotFoundInChatException;
import dev.flab.studytogether.domain.chat.repository.ChatMessageRepository;
import dev.flab.studytogether.domain.chat.repository.ChatParticipantRepository;
import dev.flab.studytogether.domain.chat.repository.ChatRepository;
import org.springframework.stereotype.Service;

@Service
public class ChatMessageValidationService {

    private final ChatRepository chatRepository;

    private final ChatParticipantRepository chatParticipantRepository;

    public ChatMessageValidationService(ChatRepository chatRepository,
                                        ChatParticipantRepository chatParticipantRepository) {
        this.chatRepository = chatRepository;
        this.chatParticipantRepository = chatParticipantRepository;
    }

    public void validate(ChatMessage chatMessage) {
        if(chatRepository.findById(chatMessage.getChatId()).isEmpty())
            throw new ChatNotFoundException(chatMessage.getChatId());

        if(chatParticipantRepository.findByChatIdAndMemberId(
                chatMessage.getChatId(),
                chatMessage.getMemberId()).isEmpty())
            throw new MemberNotFoundInChatException(chatMessage.getChatId(), chatMessage.getMemberId());
    }
}
