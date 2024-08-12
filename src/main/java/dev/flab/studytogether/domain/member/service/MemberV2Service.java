package dev.flab.studytogether.domain.member.service;

import dev.flab.studytogether.domain.member.entity.EmailAuthentication;
import dev.flab.studytogether.domain.member.entity.MemberV2;
import dev.flab.studytogether.domain.member.exception.MemberEmailAddressExistsException;
import dev.flab.studytogether.domain.member.exception.MemberNicknameExistsException;
import dev.flab.studytogether.domain.member.repository.EmailAuthenticationRepository;
import dev.flab.studytogether.domain.member.repository.MemberV2Repository;
import dev.flab.studytogether.utils.RandomUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            throw new MemberEmailAddressExistsException();
        }

        if(memberV2Repository.isNicknameExists(nickname)) {
            throw new MemberNicknameExistsException();
        }

        MemberV2 newMember = MemberV2.createNewMember(
                email,
                passwordEncoder.encode(password),
                nickname);

        String authKey = RandomUtil.generateRandomToken(16);

        EmailAuthentication emailConfirm = new EmailAuthentication(email, authKey);

        memberV2Repository.save(newMember);
        emailAuthenticationRepository.save(emailConfirm);
        notificationService.sendEmailAddressVerification(email, authKey);

        return newMember;
    }
}
