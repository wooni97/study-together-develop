package dev.flab.studytogether.domain.member.repository;

import dev.flab.studytogether.domain.member.entity.MemberV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberV2RepositoryImpl implements MemberV2Repository{
    private final MemberV2JpaRepository memberV2JpaRepository;

    @Override
    public MemberV2 save(MemberV2 member) {
         return memberV2JpaRepository.save(member);
    }

    @Override
    public boolean isEmailExists(String email) {
        return memberV2JpaRepository.existsByEmail(email);
    }

    @Override
    public boolean isNicknameExists(String nickname) {
        return memberV2JpaRepository.existsByNickname(nickname);
    }

    @Override
    public Optional<MemberV2> findByEmail(String email) {
       return memberV2JpaRepository.findByEmail(email);
    }
}
