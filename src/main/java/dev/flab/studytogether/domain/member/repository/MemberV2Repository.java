package dev.flab.studytogether.domain.member.repository;

import dev.flab.studytogether.domain.member.entity.MemberV2;
import java.util.Optional;

public interface MemberV2Repository {
    MemberV2 save(MemberV2 member);
    boolean isEmailExists(String email);
    boolean isNicknameExists(String nickname);
    Optional<MemberV2> findByEmail(String email);
}
