package dev.flab.studytogether.core.domain.member.entity;

import dev.flab.studytogether.common.utils.RandomUtil;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
public class EmailAuthentication {
    public static final long VALID_TIME = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String authKey;
    private LocalDateTime createdAt;
    private LocalDateTime validUntil;
    private boolean isExpired;

    protected EmailAuthentication() {}

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

    public void reIssueAuthKey() {
        this.authKey = RandomUtil.generateRandomToken(16);
        this.validUntil = LocalDateTime.now().plusDays(VALID_TIME);
        this.isExpired = false;
    }
}
