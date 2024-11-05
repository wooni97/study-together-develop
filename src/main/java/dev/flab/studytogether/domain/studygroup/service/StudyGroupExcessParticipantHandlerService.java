package dev.flab.studytogether.domain.studygroup.service;

import dev.flab.studytogether.domain.studygroup.entity.ParticipantV2;
import dev.flab.studytogether.domain.studygroup.entity.StudyGroup;
import dev.flab.studytogether.domain.studygroup.exception.MemberNotFoundInGroupException;
import dev.flab.studytogether.domain.studygroup.exception.StudyGroupNotFoundException;
import dev.flab.studytogether.domain.studygroup.infrastructure.StudyGroupRepositoryImpl;
import dev.flab.studytogether.domain.studygroup.repository.StudyGroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class StudyGroupExcessParticipantHandlerService {

    private final StudyGroupRepository studyGroupRepository;

    public boolean isParticipantDeactivatedIfOverCapacity(Long groupId, Long participantId) {
        StudyGroup studyGroup = studyGroupRepository.findById(groupId)
                .orElseThrow(() -> new StudyGroupNotFoundException(groupId));

        List<Long> participantIdsWithinLimit =
                studyGroupRepository.findParticipantIdsWithinLimit(studyGroup);

        if(participantIdsWithinLimit.contains(participantId))
            return false;

        ParticipantV2 participant = studyGroup.getParticipants().getParticipants()
                .stream()
                .filter(p -> participantId.equals(p.getId()))
                .findFirst()
                .orElseThrow(() -> new MemberNotFoundInGroupException(groupId, participantId));

        participant.changeParticipatingStatus(false);
        studyGroupRepository.save(studyGroup);

        return true;
    }

}
