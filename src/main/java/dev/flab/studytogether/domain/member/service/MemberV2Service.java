package dev.flab.studytogether.domain.member.service;

import dev.flab.studytogether.domain.member.entity.EmailAuthentication;
import dev.flab.studytogether.domain.member.entity.MemberV2;
import dev.flab.studytogether.domain.member.event.MemberV2SignUpEvent;
import dev.flab.studytogether.domain.member.exception.*;
import dev.flab.studytogether.domain.member.repository.EmailAuthenticationRepository;
import dev.flab.studytogether.domain.member.repository.MemberV2Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberV2Service {
    private final MemberV2Repository memberV2Repository;
    private final EmailAuthenticationRepository emailAuthenticationRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberV2SignUpEventPublishService memberV2SignUpEventPublishService;


    @Transactional
    public MemberV2 signUp(String email, String password, String nickname) {
        if(memberV2Repository.isEmailExists(email)) {
            throw new DuplicateEmailAddressException();
        }

        if(memberV2Repository.isEmailExists(nickname)) {
            throw new DuplicateNicknameException();
        }

        MemberV2 newMember = MemberV2.createNewMember(
                email,
                passwordEncoder.encode(password),
                nickname);

        EmailAuthentication emailAuthentication = createEmailAuthentication(email);

        memberV2Repository.save(newMember);
        emailAuthenticationRepository.save(emailAuthentication);

        memberV2SignUpEventPublishService.publishEvent(
                new MemberV2SignUpEvent(newMember.getEmail(), emailAuthentication.getAuthKey()));

        return newMember;
    }

    @Transactional
    public void emailAddressAuthenticate(String email, String authKey) {
        MemberV2 member = memberV2Repository.findByEmail(email)
                .orElseThrow(() -> new MemberNotFoundException("이메일 인증이 필요한 해당 회원이 존재하지 않습니다."));

        EmailAuthentication emailConfirm = emailAuthenticationRepository.findByEmailAndAuthKey(email, authKey)
                .orElseThrow(EmailAuthenticationNotFoundException::new);

        // EmailAuthentication이 만료됐을 경우
        if(emailConfirm.isExpired()) {
            throw new EmailAuthenticationExpiredException();
        }

        member.authenticateEmail();

        emailAuthenticationRepository.delete(emailConfirm);
    }


    public EmailAuthentication createEmailAuthentication(String email) {
        Optional<MemberV2> member = memberV2Repository.findByEmail(email);
        if(member.isEmpty()) {
            throw new MemberNotFoundException("존재하지 않는 회원입니다.");
        }

        if(member.get().isEmailAuthenticated()) {
            throw new EmailAlreadyAuthenticatedException();
        }

        // 기존에 EmailAuthentication 만료 시킨 후 재발급
        if(emailAuthenticationRepository.findByEmail(email).isPresent()) {
            EmailAuthentication existingEmailAuthentication = emailAuthenticationRepository.findByEmail(email).get();

            existingEmailAuthentication.reIssueAuthKey();

            return existingEmailAuthentication;
        }

        EmailAuthentication emailAuthentication = EmailAuthentication.issueNewEmailAuthentication(email);
        emailAuthenticationRepository.save(emailAuthentication);

        return emailAuthentication;
    }

    public MemberV2 login(String email, String password) {
        MemberV2 member = memberV2Repository.findByEmail(email)
                .orElseThrow(() -> new MemberNotFoundException("해당 Email이 존재하지 않습니다."));

        if(passwordEncoder.matches(password, member.getPassword())) {
            return member;
        }

        throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
    }
}
