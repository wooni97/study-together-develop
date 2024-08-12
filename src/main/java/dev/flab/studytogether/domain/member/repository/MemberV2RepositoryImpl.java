package dev.flab.studytogether.domain.member.repository;

import dev.flab.studytogether.domain.member.entity.MemberV2;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class MemberV2RepositoryImpl implements MemberV2Repository{
    @Override
    public MemberV2 save(MemberV2 member) {
        return null;
    }

    @Override
    public boolean isEmailExists(String email) {
        return false;
    }

    @Override
    public boolean isNicknameExists(String nickname) {
        return false;
    }

    @Override
    public Optional<MemberV2> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public MemberV2 update(MemberV2 member) {
        return null;
    }
}
