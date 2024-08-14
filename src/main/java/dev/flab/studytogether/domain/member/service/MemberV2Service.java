package dev.flab.studytogether.domain.member.service;

import dev.flab.studytogether.domain.member.entity.EmailAuthentication;
import dev.flab.studytogether.domain.member.entity.MemberV2;
import dev.flab.studytogether.domain.member.exception.DuplicateEmailAddressException;
import dev.flab.studytogether.domain.member.exception.DuplicateNicknameException;
import dev.flab.studytogether.domain.member.exception.EmailAlreadyAuthenticatedException;
import dev.flab.studytogether.domain.member.exception.EmailAuthenticationExpiredException;
import dev.flab.studytogether.domain.member.exception.EmailAuthenticationNotFoundException;
import dev.flab.studytogether.domain.member.exception.MemberNotFoundException;
import dev.flab.studytogether.domain.member.repository.EmailAuthenticationRepository;
import dev.flab.studytogether.domain.member.repository.MemberV2Repository;
import dev.flab.studytogether.utils.RandomUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class MemberV2Service {
    private final MemberV2Repository memberV2Repository;
    private final EmailAuthenticationRepository emailAuthenticationRepository;
    private final NotificationService notificationService;
    private final PasswordEncoder passwordEncoder;

    public MemberV2Service(MemberV2Repository memberV2Repository,
                           EmailAuthenticationRepository emailAuthenticationRepository,
                           NotificationService notificationService,
                           PasswordEncoder passwordEncoder) {
        this.memberV2Repository = memberV2Repository;
        this.emailAuthenticationRepository = emailAuthenticationRepository;
        this.notificationService = notificationService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public MemberV2 signUp(String email, String password, String nickname) {
        if(memberV2Repository.isEmailExists(email)) {
            throw new DuplicateEmailAddressException();
        }

        if(memberV2Repository.isNicknameExists(nickname)) {
            throw new DuplicateNicknameException();
        }

        MemberV2 newMember = MemberV2.createNewMember(
                email,
                passwordEncoder.encode(password),
                nickname);

        EmailAuthentication emailAuthentication = createEmailAuthentication(email);

        memberV2Repository.save(newMember);

        notificationService.sendEmailAddressVerification(email, emailAuthentication.getAuthKey());

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

        memberV2Repository.update(member);
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

            emailAuthenticationRepository.update(existingEmailAuthentication);

            return existingEmailAuthentication;
        }

        EmailAuthentication emailAuthentication = EmailAuthentication.issueNewEmailAuthentication(email);
        emailAuthenticationRepository.save(emailAuthentication);

        return emailAuthentication;
    }
}
