package dev.flab.studytogether.core.domain.studygroup.service;

import dev.flab.studytogether.core.domain.studygroup.entity.ParticipantV2;
import dev.flab.studytogether.core.domain.studygroup.entity.StudyGroup;
import dev.flab.studytogether.core.domain.studygroup.exception.StudyGroupNotFoundException;
import dev.flab.studytogether.core.domain.studygroup.repository.StudyGroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service 
@AllArgsConstructor
public class StudyGroupExitService {
    
    private final StudyGroupRepository studyGroupRepository;

    @Transactional
    public void exitGroup(Long studyGroupId, Long participantId) {
        StudyGroup studyGroup = studyGroupRepository.findById(studyGroupId)
                .orElseThrow(() ->
                        new StudyGroupNotFoundException(studyGroupId));

        ParticipantV2 participant = studyGroup.getJoinedParticipantByParticipantId(participantId);

        studyGroup.exitGroup(participant.getMemberId(), participant.getId());

        studyGroupRepository.save(studyGroup);
    }
}
