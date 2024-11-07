package dev.flab.studytogether.domain.studygroup.service;

import dev.flab.studytogether.domain.studygroup.entity.ParticipantV2;
import dev.flab.studytogether.domain.studygroup.entity.StudyGroup;
import dev.flab.studytogether.domain.studygroup.exception.StudyGroupNotFoundException;
import dev.flab.studytogether.domain.studygroup.repository.StudyGroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service 
@AllArgsConstructor
public class StudyGroupExitService {
    
    private final StudyGroupRepository studyGroupRepository;
    
    public void exitGroup(Long studyGroupId, Long participantId) {
        StudyGroup studyGroup = studyGroupRepository.findById(studyGroupId)
                .orElseThrow(() ->
                        new StudyGroupNotFoundException(studyGroupId));

        ParticipantV2 participant = studyGroup.getParticipantByParticipantId(participantId);

        studyGroup.exitGroup(participant.getMemberId(), participant.getId());

        studyGroupRepository.save(studyGroup);
    }
}
