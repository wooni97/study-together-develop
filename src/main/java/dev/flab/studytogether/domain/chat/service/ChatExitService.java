package dev.flab.studytogether.domain.chat.service;

import dev.flab.studytogether.domain.chat.repository.ChatParticipantRepository;
import org.springframework.stereotype.Service;

@Service
public class ChatExitService {

    private final ChatParticipantRepository chatParticipantRepository;

    public ChatExitService(ChatParticipantRepository chatParticipantRepository) {
        this.chatParticipantRepository = chatParticipantRepository;
    }

    public void exitChat(Long chatId, Long memberId) {
        chatParticipantRepository.deleteByChatIdAndMemberId(chatId, memberId);
    }
}
