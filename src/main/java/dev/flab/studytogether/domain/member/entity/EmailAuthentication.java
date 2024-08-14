package dev.flab.studytogether.domain.member.entity;

import dev.flab.studytogether.utils.RandomUtil;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class EmailAuthentication {
    public static final long VALID_TIME = 1L;

    private long id;
    private final String email;
    private final String authKey;
    private final LocalDateTime createdAt;
    private final LocalDateTime validUntil;
    private boolean isExpired;

    private EmailAuthentication(String email, String authKey) {
        this.email = email;
        this.authKey = authKey;
        this.createdAt = LocalDateTime.now();
        this.validUntil = createdAt.plusDays(VALID_TIME);
        this.isExpired = false;
    }

    private EmailAuthentication(long id, String email, String authKey) {
        this.id = id;
        this.email = email;
        this.authKey = authKey;
        this.createdAt = LocalDateTime.now();
        this.validUntil = createdAt.plusDays(VALID_TIME);
        this.isExpired = false;
    }

    public EmailAuthentication(long id, String email, String authKey, LocalDateTime createdAt, LocalDateTime expiredDateTime, boolean isExpired) {
        this.id = id;
        this.email = email;
        this.authKey = authKey;
        this.createdAt = createdAt;
        this.validUntil = expiredDateTime;
        this.isExpired = isExpired;
    }

    public boolean isExpired() {
        if(isExpired) return true;

        if(LocalDateTime.now().isAfter(validUntil)) return true;

        return false;
    }

    public void expire() {
        this.isExpired = true;
    }

    public static EmailAuthentication issueNewEmailAuthentication(String email) {
        return new EmailAuthentication(email, RandomUtil.generateRandomToken(16));
    }

    public static EmailAuthentication reIssueEmailAuthentication(long id, String email) {
        return new EmailAuthentication(id, email, RandomUtil.generateRandomToken(16));
    }
}
