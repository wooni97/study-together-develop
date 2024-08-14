package dev.flab.studytogether.domain.member.repository;

import dev.flab.studytogether.domain.member.entity.EmailAuthentication;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
// TODO 추후 구현 예정
@Repository
public class EmailAuthenticationRepositoryImpl implements EmailAuthenticationRepository{
    @Override
    public EmailAuthentication save(EmailAuthentication emailConfirm) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<EmailAuthentication> findByEmailAndAuthKey(String email, String authKey) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<EmailAuthentication> findByEmail(String email) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void delete(EmailAuthentication emailAuthentication) {
        throw new UnsupportedOperationException();
    }

    @Override
    public EmailAuthentication update(EmailAuthentication emailConfirm) {
        throw new UnsupportedOperationException();
    }
}
