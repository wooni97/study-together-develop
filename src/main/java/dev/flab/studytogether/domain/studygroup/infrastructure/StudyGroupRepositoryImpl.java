package dev.flab.studytogether.domain.studygroup.infrastructure;

import dev.flab.studytogether.domain.room.entity.ActivateStatus;
import dev.flab.studytogether.domain.studygroup.entity.StudyGroup;
import dev.flab.studytogether.domain.studygroup.repository.StudyGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StudyGroupRepositoryImpl implements StudyGroupRepository {
    @Autowired
    StudyGroupJpaRepository studyGroupJpaRepository;

    @Override
    public StudyGroup save(StudyGroup studyGroup) {
        return studyGroupJpaRepository.save(studyGroup);
    }

    @Override
    public Optional<StudyGroup> findById(long id) {
        return studyGroupJpaRepository.findById(id);
    }

    @Override
    public List<StudyGroup> findByActivateStatus(ActivateStatus status) {
        return studyGroupJpaRepository.findByActivateStatus(status);
    }
}
