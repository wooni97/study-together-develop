package dev.flab.studytogether.domain.studygroup.repository;

import dev.flab.studytogether.domain.room.entity.ActivateStatus;
import dev.flab.studytogether.domain.studygroup.entity.StudyGroup;

import java.util.List;
import java.util.Optional;

public interface StudyGroupRepository {
    StudyGroup save(StudyGroup studyGroup);
    Optional<StudyGroup> findById(long id);
    List<StudyGroup> findByActivateStatus(ActivateStatus status);
    List<Long> findParticipantIdsWithinLimit(StudyGroup studyGroup);
}
