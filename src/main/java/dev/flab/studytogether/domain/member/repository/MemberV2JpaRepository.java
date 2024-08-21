package dev.flab.studytogether.domain.member.repository;

import dev.flab.studytogether.domain.member.entity.MemberV2;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MemberV2JpaRepository extends JpaRepository<MemberV2, Long> {
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickName);
    Optional<MemberV2> findByEmail(String email);
}
