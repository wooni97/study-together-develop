package dev.flab.studytogether.domain.studygroup.infrastructure;

import dev.flab.studytogether.domain.studygroup.entity.StudyGroup;
import dev.flab.studytogether.domain.studygroup.repository.StudyGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class StudyGroupRepositoryImpl implements StudyGroupRepository {
    @Autowired
    private StudyGroupJpaRepository studyGroupJpaRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public StudyGroup save(StudyGroup studyGroup) {
        return studyGroupJpaRepository.save(studyGroup);
    }

    @Override
    public Optional<StudyGroup> findById(long id) {
        return studyGroupJpaRepository.findById(id);
    }
}
