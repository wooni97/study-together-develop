package dev.flab.studytogether.domain.member.entity;

import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class EmailAuthentication {
    public static final long VALID_TIME = 1L;

    private long id;
    private final String email;
    private final String authKey;
    private final LocalDateTime createdAt;
    private final LocalDateTime expiredDateTime;

    public EmailAuthentication(String email, String token) {
        this.email = email;
        this.authKey = token;
        this.createdAt = LocalDateTime.now();
        this.expiredDateTime = createdAt.plusDays(VALID_TIME);
    }

    public EmailAuthentication(long id, String email, String token, LocalDateTime createdAt, LocalDateTime expiredDateTime, boolean used) {
        this.id = id;
        this.email = email;
        this.authKey = token;
        this.createdAt = createdAt;
        this.expiredDateTime = expiredDateTime;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiredDateTime);
    }
}
