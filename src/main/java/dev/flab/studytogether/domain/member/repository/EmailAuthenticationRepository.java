package dev.flab.studytogether.domain.member.repository;

import dev.flab.studytogether.domain.member.entity.EmailAuthentication;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EmailAuthenticationRepository {
    EmailAuthentication save(EmailAuthentication emailConfirm);
    Optional<EmailAuthentication> findByEmailAndAuthKey(String email, String authKey);
    EmailAuthentication update(EmailAuthentication emailConfirm);
}
