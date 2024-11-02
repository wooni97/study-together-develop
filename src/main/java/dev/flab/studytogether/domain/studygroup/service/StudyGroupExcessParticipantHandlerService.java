package dev.flab.studytogether.domain.studygroup.service;

import dev.flab.studytogether.domain.studygroup.entity.ParticipantV2;
import dev.flab.studytogether.domain.studygroup.entity.StudyGroup;
import dev.flab.studytogether.domain.studygroup.exception.StudyGroupNotFoundException;
import dev.flab.studytogether.domain.studygroup.repository.StudyGroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class StudyGroupExcessParticipantHandlerService {

    private final StudyGroupRepository studyGroupRepository;

    public List<Long> handleExcessParticipants(Long groupId) {
        StudyGroup studyGroup = studyGroupRepository.findById(groupId)
                .orElseThrow(() -> new StudyGroupNotFoundException(groupId));

        List<Long> latestParticipantIds = new ArrayList<>();
        int excessParticipantCount = studyGroup.getCurrentParticipantsCount()
                                    - studyGroup.getMaxParticipants();
        if(excessParticipantCount > 0) {
            List<ParticipantV2> latestParticipants = studyGroup
                                                    .getParticipants()
                                                    .getLastNEntries(excessParticipantCount);

            for(ParticipantV2 participant : latestParticipants) {
                participant.changeParticipatingStatus(false);
                latestParticipantIds.add(participant.getId());
            }
        }

        studyGroupRepository.save(studyGroup);
        return latestParticipantIds;
    }

}
