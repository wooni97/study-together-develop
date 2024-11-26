package dev.flab.studytogether.core.domain.studygroup.event;

import dev.flab.studytogether.core.domain.event.DomainEvent;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class StudyGroupJoinEvent extends DomainEvent {
    private final Long studyGroupId;
    private final Long memberId;

    private StudyGroupJoinEvent(Long studyGroupId, Long memberId) {
        super(LocalDateTime.now());
        this.studyGroupId = studyGroupId;
        this.memberId = memberId;
    }
    private StudyGroupJoinEvent(Long studyGroupId, LocalDateTime createdAt, Long memberId) {
        super(createdAt);
        this.studyGroupId = studyGroupId;
        this.memberId = memberId;
    }

    public static StudyGroupJoinEvent createNewEvent(Long studyGroupId, Long memberId) {
        return new StudyGroupJoinEvent(studyGroupId, memberId);
    }

    public static StudyGroupJoinEvent createFromExisting(Long studyGroupId, Long memberId, LocalDateTime createdAt) {
        return new StudyGroupJoinEvent(studyGroupId, createdAt, memberId);
    }
}
