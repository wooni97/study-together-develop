package dev.flab.studytogether.domain.event.infrastructure.rowmapper;

import dev.flab.studytogether.domain.studygroup.event.StudyGroupJoinEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudyGroupJoinEventRowMapper implements DomainEventRowMapper<StudyGroupJoinEvent> {
    private Long studyGroupId;
    private Long participantId;
    private LocalDateTime createdAt;

    @Override
    public StudyGroupJoinEvent createDomainEvent() {
        return StudyGroupJoinEvent.createFromExisting(studyGroupId, participantId, createdAt);
    }

    @Override
    public Class<StudyGroupJoinEvent> eventType() {
        return StudyGroupJoinEvent.class;
    }
}
