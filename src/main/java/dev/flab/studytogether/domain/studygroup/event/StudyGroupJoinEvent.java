package dev.flab.studytogether.domain.studygroup.event;

import dev.flab.studytogether.domain.event.DomainEvent;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class StudyGroupJoinEvent extends DomainEvent {
    private final Long studyGroupId;
    private final Long participantId;

    private StudyGroupJoinEvent(Long studyGroupId, Long participantId) {
        super(LocalDateTime.now());
        this.studyGroupId = studyGroupId;
        this.participantId = participantId;
    }
    private StudyGroupJoinEvent(Long studyGroupId, LocalDateTime createdAt, Long participantId) {
        super(createdAt);
        this.studyGroupId = studyGroupId;
        this.participantId = participantId;
    }

    public static StudyGroupJoinEvent createNewEvent(Long studyGroupId, Long participantId) {
        return new StudyGroupJoinEvent(studyGroupId, participantId);
    }

    public static StudyGroupJoinEvent createFromExisting(Long studyGroupId, Long participantId, LocalDateTime createdAt) {
        return new StudyGroupJoinEvent(studyGroupId, createdAt, participantId);
    }
}
