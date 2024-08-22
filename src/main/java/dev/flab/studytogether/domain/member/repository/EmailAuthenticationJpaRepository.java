package dev.flab.studytogether.domain.member.repository;

import dev.flab.studytogether.domain.member.entity.EmailAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EmailAuthenticationJpaRepository extends JpaRepository<EmailAuthentication, Long> {
    Optional<EmailAuthentication> findByEmailAndAuthKey(String email, String authKey);
    Optional<EmailAuthentication> findByEmail(String email);

}
