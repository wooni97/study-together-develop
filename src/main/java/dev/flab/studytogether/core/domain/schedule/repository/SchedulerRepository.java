package dev.flab.studytogether.core.domain.schedule.repository;

import dev.flab.studytogether.core.domain.schedule.entity.Scheduler;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface SchedulerRepository {



    Optional<Scheduler> findByMemberIdAndDate(int memberSequenceId, LocalDate date);

    Scheduler save(LocalDate date, int seqId);
}
