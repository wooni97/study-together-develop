package dev.flab.studytogether.core.domain.notification.service;

import dev.flab.studytogether.core.domain.notification.AbstractNotificationData;
import dev.flab.studytogether.core.domain.notification.MailNotificationData;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailNotificationSender extends NotificationSender{

    private final JavaMailSender javaMailSender;

    public MailNotificationSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public Class<? extends AbstractNotificationData> getSupportedDataTypeClass() {
        return MailNotificationData.class;
    }

    @Override
    public void send(AbstractNotificationData notificationData) {
        if(!(notificationData instanceof MailNotificationData mailNotification))
            throw new ClassCastException("Expected MailNotification, but received: " + notificationData.getClass().getSimpleName());

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(mailNotification.getRecipientEmailAddress());
            mimeMessageHelper.setSubject(mailNotification.getSubject());
            mimeMessageHelper.setText(mailNotification.getNotificationMessageContent());

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
