package dev.flab.studytogether.domain.studygroup.service;

import dev.flab.studytogether.domain.chat.entity.StudyGroupChat;
import dev.flab.studytogether.domain.chat.exception.StudyGroupChatNotFoundException;
import dev.flab.studytogether.domain.chat.repository.StudyGroupChatRepository;
import dev.flab.studytogether.domain.chat.service.ChatExitService;
import dev.flab.studytogether.domain.studygroup.entity.ParticipantV2;
import dev.flab.studytogether.domain.studygroup.entity.StudyGroup;
import dev.flab.studytogether.domain.studygroup.exception.StudyGroupNotFoundException;
import dev.flab.studytogether.domain.studygroup.repository.StudyGroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service 
@AllArgsConstructor
public class StudyGroupExitService {
    
    private final StudyGroupRepository studyGroupRepository;
    private final StudyGroupChatRepository studyGroupChatRepository;
    private final ChatExitService chatExitService;

    @Transactional
    public void exitGroup(Long studyGroupId, Long participantId) {
        StudyGroup studyGroup = studyGroupRepository.findById(studyGroupId)
                .orElseThrow(() ->
                        new StudyGroupNotFoundException(studyGroupId));

        ParticipantV2 participant = studyGroup.getJoinedParticipantByParticipantId(participantId);

        studyGroup.exitGroup(participant.getMemberId(), participant.getId());

        StudyGroupChat studyGroupChat  = studyGroupChatRepository.findByStudyGroupId(studyGroupId)
                        .orElseThrow(() -> new StudyGroupChatNotFoundException(studyGroupId));
        chatExitService.exitChat(studyGroupChat.getId(), participant.getMemberId());

        studyGroupRepository.save(studyGroup);
    }
}
