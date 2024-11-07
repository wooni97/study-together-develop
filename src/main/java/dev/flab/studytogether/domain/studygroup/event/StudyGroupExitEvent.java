package dev.flab.studytogether.domain.studygroup.event;

import dev.flab.studytogether.domain.event.DomainEvent;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class StudyGroupExitEvent extends DomainEvent {

    private final Long studyGroupId;
    private final Long participantId;

    private StudyGroupExitEvent(Long studyGroupId, Long participantId) {
        super(LocalDateTime.now());
        this.studyGroupId = studyGroupId;
        this.participantId = participantId;
    }

    private StudyGroupExitEvent(Long studyGroupId, Long participantId, LocalDateTime createdAt) {
        super(createdAt);
        this.studyGroupId = studyGroupId;
        this.participantId = participantId;
    }

    public static StudyGroupExitEvent createNewEvent(Long studyGroupId, Long participantId) {
        return new StudyGroupExitEvent(studyGroupId, participantId);
    }

    public static StudyGroupExitEvent createFromExisting(Long studyGroupId, Long participantId, LocalDateTime createdAt) {
        return new StudyGroupExitEvent(studyGroupId, participantId, createdAt);
    }
}
