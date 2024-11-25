package dev.flab.studytogether.core.domain.member.repository;

import dev.flab.studytogether.core.domain.member.entity.EmailAuthentication;
import java.util.Optional;

public interface EmailAuthenticationRepository {
    EmailAuthentication save(EmailAuthentication emailConfirm);
    Optional<EmailAuthentication> findByEmailAndAuthKey(String email, String authKey);
    Optional<EmailAuthentication> findByEmail(String email);
    void delete(EmailAuthentication emailAuthentication);
}
