package dev.flab.studytogether.domain.member.service;

import dev.flab.studytogether.domain.member.entity.EmailAuthentication;
import dev.flab.studytogether.domain.member.entity.MemberV2;
import dev.flab.studytogether.domain.member.exception.DuplicateEmailAddressException;
import dev.flab.studytogether.domain.member.exception.DuplicateNicknameException;
import dev.flab.studytogether.domain.member.exception.EmailAlreadyAuthenticatedException;
import dev.flab.studytogether.domain.member.repository.EmailAuthenticationRepository;
import dev.flab.studytogether.domain.member.repository.MemberV2Repository;
import dev.flab.studytogether.utils.RandomUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

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

    public EmailAuthentication createEmailAuthentication(String email) {
        // 이미 이메일 인증이 된 사용자일 경우
        if(memberV2Repository.findByEmail(email).get().isEmailAuthenticated()) {
            throw new EmailAlreadyAuthenticatedException();
        }

        // 기존에 유효한 EmailAuthentication 만료 시킴
        List<EmailAuthentication> existingEmailAuthentications = emailAuthenticationRepository.findByEmail(email);
        if(!existingEmailAuthentications.isEmpty()) {
            existingEmailAuthentications.stream()
                    .filter(emailAuthentication -> !emailAuthentication.isExpired())
                    .forEach(emailAuthentication -> {
                        emailAuthentication.expire();
                        emailAuthenticationRepository.update(emailAuthentication);
                    });
        }

        // 새 authKey 발급
        String authKey = RandomUtil.generateRandomToken(16);
        EmailAuthentication emailAuthentication = new EmailAuthentication(email, authKey);
        emailAuthenticationRepository.save(emailAuthentication);

        return emailAuthentication;
    }
}
