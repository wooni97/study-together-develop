package dev.flab.studytogether.core.domain.studygroup.event;

import dev.flab.studytogether.core.domain.event.DomainEvent;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class StudyGroupCreatedEvent extends DomainEvent {

    private final Long studyGroupId;

    private StudyGroupCreatedEvent(Long studyGroupId) {
        super(LocalDateTime.now());
        this.studyGroupId = studyGroupId;
    }

    private StudyGroupCreatedEvent(Long studyGroupId, LocalDateTime cratedAt) {
        super(cratedAt);
        this.studyGroupId = studyGroupId;
    }

    public static StudyGroupCreatedEvent createNewEvent(Long studyGroupId) {
        return new StudyGroupCreatedEvent(studyGroupId);
    }

    public static StudyGroupCreatedEvent createFromExisting(Long studyGroupId, LocalDateTime createdAt) {
        return new StudyGroupCreatedEvent(studyGroupId, createdAt);
    }
}
