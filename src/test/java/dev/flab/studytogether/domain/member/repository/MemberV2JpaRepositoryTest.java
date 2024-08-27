package dev.flab.studytogether.domain.member.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import dev.flab.studytogether.domain.member.entity.MemberV2;
import dev.flab.studytogether.util.TestFixtureUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.junit.jupiter.api.*;
import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
class MemberV2JpaRepositoryTest {
    @Autowired
    private MemberV2JpaRepository memberV2JpaRepository;

    @Test
    @DisplayName("Member를 save하면 sequenceID가 부여된 Member 객체를 반환한다.")
    void whenMemberIsSaved_thenReturnsMemberWithSequenceId() {
        //given
        MemberV2 newMember = TestFixtureUtils.randomMember();

        //when
        MemberV2 savedMember = memberV2JpaRepository.save(newMember);

        //then
        assertNotNull(savedMember);
        assertThat(savedMember.getId()).isPositive();
    }

    @Test
    @DisplayName("Member를 저장할 때, Member 정보가 올바르게 저장되는지 확인")
    void shouldSaveMemberWithCorrectDetails() {
        //given
        MemberV2 newMember = TestFixtureUtils.randomMember();

        //when
        MemberV2 savedMember = memberV2JpaRepository.save(newMember);

        //then
        assertEquals(newMember.getEmail(), savedMember.getEmail());
        assertEquals(newMember.getNickname(), savedMember.getNickname());
        assertEquals(newMember.getPassword(), savedMember.getPassword());
    }
    @Test
    @DisplayName("데이터베이스에 존재하는 회원 Email을 가진 회원 존재 여부를 확인하면 true를 반환한다.")
    void whenCheckEmailExistsWithExistingMemberEmail_thenReturnsTrue() {
        //given
        MemberV2 newMember = TestFixtureUtils.randomMember();
        memberV2JpaRepository.save(newMember);

        //when, then
        assertThat(memberV2JpaRepository.existsByEmail(newMember.getEmail()))
                .isTrue();
    }

    @Test
    @DisplayName("데이터베이스에 존재하는 닉네임을 가진 회원 존재 여부를 확인하면 true를 반환한다.")
    void whenCheckNicknameExistsWithExistingNickname_thenReturnsTrue() {
        //given
        MemberV2 newMember = TestFixtureUtils.randomMember();
        memberV2JpaRepository.save(newMember);

        //when, then
        assertThat(memberV2JpaRepository.existsByNickname(newMember.getNickname()))
                .isTrue();
    }

    @Test
    @DisplayName("존재하는 회원 이메일로 회원을 찾을 때, 해당 회원이 반환되어야 한다.")
    void whenFindMemberByExistingMemberEmail_thenReturnsTheMember() {
        //given
        MemberV2 newMember = TestFixtureUtils.randomMember();
        memberV2JpaRepository.save(newMember);

        //when
        Optional<MemberV2> returnedMember = memberV2JpaRepository.findByEmail(newMember.getEmail());

        //then
        assertThat(returnedMember).isPresent();
    }

    @Test
    @DisplayName("존재하지 않는 회원 이메일로 회원을 찾을때, Optional.empty()가 반환되어야 한다.")
    void whenFindMemberByNotExistingMemberEmail_thenReturnsEmpty() {
        //when
        String nonExistingEmail = "testEmail@gmail.com";
        Optional<MemberV2> returnedMember = memberV2JpaRepository.findByEmail(nonExistingEmail);

        //then
        assertThat(returnedMember).isEmpty();
    }
    
}
