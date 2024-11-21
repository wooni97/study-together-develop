package dev.flab.studytogether.domain.studygroup.event;

import dev.flab.studytogether.domain.event.DomainEvent;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class StudyGroupCreatedEvent extends DomainEvent {

    private final Long studyGroupId;

    private StudyGroupCreatedEvent(Long studyGroupId) {
        super(LocalDateTime.now());
        this.studyGroupId = studyGroupId;
    }

    public static StudyGroupCreatedEvent createNewEvent(Long studyGroupId) {
        return new StudyGroupCreatedEvent(studyGroupId);
    }
}
