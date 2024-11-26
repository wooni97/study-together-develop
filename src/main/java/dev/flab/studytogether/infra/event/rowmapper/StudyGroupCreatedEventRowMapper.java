package dev.flab.studytogether.infra.event.rowmapper;

import dev.flab.studytogether.core.domain.studygroup.event.StudyGroupCreatedEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudyGroupCreatedEventRowMapper implements DomainEventRowMapper<StudyGroupCreatedEvent>{

    private Long studyGroupId;
    private LocalDateTime createdAt;

    @Override
    public StudyGroupCreatedEvent createDomainEvent() {
        return StudyGroupCreatedEvent.createFromExisting(studyGroupId, createdAt);
    }

    @Override
    public Class<StudyGroupCreatedEvent> eventType() {
        return StudyGroupCreatedEvent.class;
    }
}
