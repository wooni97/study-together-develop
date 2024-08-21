package dev.flab.studytogether.domain.member.repository;

import dev.flab.studytogether.domain.member.entity.EmailAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EmailAuthenticationRepositoryImpl implements EmailAuthenticationRepository{
    private final EmailAuthenticationJpaRepository emailAuthenticationJpaRepository;
    @Override
    public EmailAuthentication save(EmailAuthentication emailAuthentication) {
        return emailAuthenticationJpaRepository.save(emailAuthentication);
    }

    @Override
    public Optional<EmailAuthentication> findByEmailAndAuthKey(String email, String authKey) {
        return emailAuthenticationJpaRepository.findByEmailAndAuthKey(email, authKey);
    }

    @Override
    public Optional<EmailAuthentication> findByEmail(String email) {
        return emailAuthenticationJpaRepository.findByEmail(email);
    }

    @Override
    public void delete(EmailAuthentication emailAuthentication) {
        emailAuthenticationJpaRepository.delete(emailAuthentication);
    }
}
