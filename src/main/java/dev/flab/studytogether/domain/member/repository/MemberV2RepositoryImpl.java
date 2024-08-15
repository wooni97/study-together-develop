package dev.flab.studytogether.domain.member.repository;

import dev.flab.studytogether.domain.member.entity.MemberV2;
import org.springframework.stereotype.Repository;

import java.util.Optional;


// TODO 추후 구현 예정
@Repository
public class MemberV2RepositoryImpl implements MemberV2Repository{
    @Override
    public MemberV2 save(MemberV2 member) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmailExists(String email) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isNicknameExists(String nickname) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<MemberV2> findByEmail(String email) {
        throw new UnsupportedOperationException();
    }

    @Override
    public MemberV2 update(MemberV2 member) {
        throw new UnsupportedOperationException();
    }
}
