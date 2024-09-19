package dev.flab.studytogether.domain.studygroup.repository;


import dev.flab.studytogether.domain.studygroup.entity.ParticipantV2;

public interface ParticipantV2Repository {
    ParticipantV2 save(ParticipantV2 participant);
    void delete(ParticipantV2 participant);

}
