package dev.flab.studytogether.domain.member.event;

import lombok.Getter;

@Getter
public class MemberV2SignUpEvent {
    private final Long id;
    private final String email;
    private final String authKey;

    public MemberV2SignUpEvent(Long id, String email, String authKey) {
        this.id = id;
        this.email = email;
        this.authKey = authKey;
    }

    public MemberV2SignUpEvent(String email, String authKey) {
        this.id = null;
        this.email = email;
        this.authKey = authKey;
    }
}
