package dev.flab.studytogether.domain.member.repository;

import dev.flab.studytogether.domain.member.entity.EmailAuthentication;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class EmailAuthenticationRepositoryImpl implements EmailAuthenticationRepository{
    @Override
    public EmailAuthentication save(EmailAuthentication emailConfirm) {
        return null;
    }

    @Override
    public Optional<EmailAuthentication> findByEmailAndAuthKey(String email, String authKey) {
        return Optional.empty();
    }

    @Override
    public EmailAuthentication update(EmailAuthentication emailConfirm) {
        return null;
    }
}
