package dev.flab.studytogether.domain.studygroup.infrastructure;

import dev.flab.studytogether.domain.studygroup.entity.StudyGroup;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudyGroupJpaRepository extends JpaRepository<StudyGroup, Long> {
    @EntityGraph(attributePaths = {"participants.participants"})
    Optional<StudyGroup> findById(long id);
}
