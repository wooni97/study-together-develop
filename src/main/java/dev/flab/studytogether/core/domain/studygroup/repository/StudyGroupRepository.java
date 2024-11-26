package dev.flab.studytogether.core.domain.studygroup.repository;

import dev.flab.studytogether.core.domain.studygroup.entity.StudyGroup;

import java.util.Optional;

public interface StudyGroupRepository {
    StudyGroup save(StudyGroup studyGroup);
    Optional<StudyGroup> findById(long id);
}
