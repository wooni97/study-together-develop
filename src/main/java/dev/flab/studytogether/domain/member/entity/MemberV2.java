package dev.flab.studytogether.domain.member.entity;

import lombok.Getter;

@Getter
public class MemberV2 {
    private long id;
    private final String email;
    private boolean emailAuthenticated;
    private final String password;
    private final String nickname;

    private MemberV2(String email, String password, String nickname) {
        this.email = email;
        this.emailAuthenticated = false;
        this.password = password;
        this.nickname = nickname;
    }

    public static MemberV2 createNewMember(String email, String password, String nickname) {
        return new MemberV2(email, password, nickname);
    }

    public void authenticateEmail() {
        if (emailAuthenticated) {
            throw new EmailAlreadyAuthenticatedException();
        }
        this.emailAuthenticated = true;
    }
}