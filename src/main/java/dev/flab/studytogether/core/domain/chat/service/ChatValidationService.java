package dev.flab.studytogether.core.domain.chat.service;

import dev.flab.studytogether.core.domain.chat.entity.Chat;
import dev.flab.studytogether.core.domain.chat.entity.StudyGroupChat;
import dev.flab.studytogether.core.domain.chat.exception.ChatNotFoundException;
import dev.flab.studytogether.core.domain.chat.exception.MemberNotFoundInChatException;
import dev.flab.studytogether.core.domain.chat.repository.ChatRepository;
import dev.flab.studytogether.core.domain.studygroup.entity.ParticipantsV2;
import dev.flab.studytogether.core.domain.studygroup.entity.StudyGroup;
import dev.flab.studytogether.core.domain.studygroup.exception.StudyGroupNotFoundException;
import dev.flab.studytogether.core.domain.studygroup.repository.StudyGroupRepository;
import org.springframework.stereotype.Service;

@Service
public class ChatValidationService {

    private final ChatRepository chatRepository;
    private final StudyGroupRepository studyGroupRepository;

    public ChatValidationService(ChatRepository chatRepository,
                                 StudyGroupRepository studyGroupRepository) {
        this.chatRepository = chatRepository;
        this.studyGroupRepository = studyGroupRepository;
    }

    public void validate(Long chatId, Long memberId) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new ChatNotFoundException(chatId));

        if(chat instanceof StudyGroupChat)
            studyGroupMemberValidate((StudyGroupChat) chat, memberId);
    }

    private void studyGroupMemberValidate(StudyGroupChat studyGroupChat,
                                          Long memberId) {
        StudyGroup studyGroup = studyGroupRepository.findById(studyGroupChat.getStudyGroupId())
                .orElseThrow(() ->
                        new StudyGroupNotFoundException(studyGroupChat.getStudyGroupId()));

        ParticipantsV2 participants = studyGroup.getParticipants();

        if(!participants.isMemberJoined(memberId))
            throw new MemberNotFoundInChatException(studyGroupChat.getId(), memberId);
    }
}
