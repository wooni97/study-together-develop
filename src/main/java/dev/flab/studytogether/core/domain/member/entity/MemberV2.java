package dev.flab.studytogether.core.domain.member.entity;

import dev.flab.studytogether.core.domain.member.exception.EmailAlreadyAuthenticatedException;
import lombok.Getter;
import javax.persistence.*;

@Entity
@Getter
public class MemberV2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private boolean emailAuthenticated;
    private String password;
    private String nickname;

    protected MemberV2() {}

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
