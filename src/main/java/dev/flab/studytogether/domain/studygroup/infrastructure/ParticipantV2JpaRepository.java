package dev.flab.studytogether.domain.studygroup.infrastructure;


import dev.flab.studytogether.domain.studygroup.entity.ParticipantV2;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantV2JpaRepository extends JpaRepository<ParticipantV2, Long> {

}
