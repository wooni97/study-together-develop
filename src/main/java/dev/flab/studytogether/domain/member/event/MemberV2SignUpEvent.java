package dev.flab.studytogether.domain.member.event;

import dev.flab.studytogether.domain.event.AbstractEvent;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemberV2SignUpEvent extends AbstractEvent {
    private final Long userId;
    private final String userEmail;
    private final String authKey;

    private MemberV2SignUpEvent(Long userId, String userEmail, String authKey) {
        super(LocalDateTime.now());
        this.userId = userId;
        this.userEmail = userEmail;
        this.authKey = authKey;

    }

    public static MemberV2SignUpEvent createEvent(Long userId, String userEmail, String authKey) {
        return new MemberV2SignUpEvent(userId, userEmail, authKey);
    }
}
