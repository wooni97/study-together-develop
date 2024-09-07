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

    public void sendEmail(String email, String subject, String content) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content);

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
