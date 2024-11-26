package dev.flab.studytogether.infra.repository.member;

import dev.flab.studytogether.core.domain.member.entity.MemberV2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MemberV2JpaRepository extends JpaRepository<MemberV2, Long> {
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickName);
    Optional<MemberV2> findByEmail(String email);
}
