package dev.flab.studytogether.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private static final String EMAIL_ADDRESS_VERIFY_MAIL_SUBJECT = "회원 가입 이메일 인증";
    private final JavaMailSender javaMailSender;

    @Async
    public void sendEmailAddressVerification(String email, String authKey) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject(EMAIL_ADDRESS_VERIFY_MAIL_SUBJECT);
            mimeMessageHelper.setText("<h1>[이메일 인증]</h1>" +
                    "<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>" +
                    "<a href='http://member/signUpConfirm?email=" +
                    email +
                    "&authKey=" +
                    authKey +
                    "' target='_blenk'>이메일 인증 확인</a>");

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
