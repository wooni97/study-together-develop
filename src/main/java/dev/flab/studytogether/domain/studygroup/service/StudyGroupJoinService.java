package dev.flab.studytogether.domain.studygroup.service;

import dev.flab.studytogether.domain.chat.entity.StudyGroupChat;
import dev.flab.studytogether.domain.chat.exception.StudyGroupChatNotFoundException;
import dev.flab.studytogether.domain.chat.repository.StudyGroupChatRepository;
import dev.flab.studytogether.domain.chat.service.ChatEnterService;
import dev.flab.studytogether.domain.studygroup.entity.ParticipantV2;
import dev.flab.studytogether.domain.studygroup.entity.StudyGroup;
import dev.flab.studytogether.domain.studygroup.exception.StudyGroupNotFoundException;
import dev.flab.studytogether.domain.studygroup.repository.StudyGroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class StudyGroupJoinService {

    private final StudyGroupRepository studyGroupRepository;
    private final StudyGroupChatRepository studyGroupChatRepository;
    private final ChatEnterService chatEnterService;

    @Transactional
    public ParticipantV2 joinGroup(Long groupId, Long memberId) {
        StudyGroup studyGroup = studyGroupRepository.findById(groupId)
                .orElseThrow(() -> new StudyGroupNotFoundException(groupId));

        studyGroup.joinGroup(new ParticipantV2(studyGroup,
                memberId,
                ParticipantV2.Role.ORDINARY_PARTICIPANT,
                ParticipantV2.ParticipantStatus.JOINED,
                LocalDateTime.now()));
        studyGroupRepository.save(studyGroup);

        StudyGroupChat studyGroupChat = studyGroupChatRepository.findByStudyGroupId(groupId)
                .orElseThrow(() -> new StudyGroupChatNotFoundException(groupId));

        chatEnterService.chatEnter(studyGroupChat.getStudyGroupId(), memberId);

        return studyGroup.getJoinedParticipantByMemberId(memberId);
    }
}
