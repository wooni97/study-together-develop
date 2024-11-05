package dev.flab.studytogether.domain.studygroup.infrastructure;

import dev.flab.studytogether.domain.room.entity.ActivateStatus;
import dev.flab.studytogether.domain.studygroup.entity.ParticipantV2;
import dev.flab.studytogether.domain.studygroup.entity.StudyGroup;
import dev.flab.studytogether.domain.studygroup.repository.StudyGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
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

    @Override
    public List<StudyGroup> findByActivateStatus(ActivateStatus status) {
        return studyGroupJpaRepository.findByActivateStatus(status);
    }

    @Override
    public List<Long> findParticipantIdsWithinLimit(StudyGroup studyGroup) {
        String query = "SELECT id FROM participantv2 " +
                "WHERE study_group_id = ? " +
                "and is_participating = true " +
                "ORDER BY joined_at ASC " +
                "LIMIT ?";

        return jdbcTemplate.queryForList(
                query,
                Long.TYPE,
                studyGroup.getId(),
                studyGroup.getMaxParticipants()
        );
    }
}
