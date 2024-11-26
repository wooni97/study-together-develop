package dev.flab.studytogether.core.domain.member.event;

import dev.flab.studytogether.core.domain.event.DomainEvent;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemberV2SignUpEvent extends DomainEvent {
    private final Long userId;
    private final String userEmail;
    private final String authKey;

    private MemberV2SignUpEvent(Long userId, String userEmail, String authKey) {
        super(LocalDateTime.now());
        this.userId = userId;
        this.userEmail = userEmail;
        this.authKey = authKey;

    }

    private MemberV2SignUpEvent(Long userId, String userEmail, String authKey, LocalDateTime createdAt) {
        super(createdAt);
        this.userId = userId;
        this.userEmail = userEmail;
        this.authKey = authKey;
    }

    public static MemberV2SignUpEvent createNewEvent(Long userId, String userEmail, String authKey) {
        return new MemberV2SignUpEvent(userId, userEmail, authKey);
    }

    public static MemberV2SignUpEvent createFromExisting(Long userId, String userEmail, String authKey, LocalDateTime createdAt) {
        return new MemberV2SignUpEvent(userId, userEmail, authKey, createdAt);
    }
}
