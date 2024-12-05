package dev.flab.studytogether.core.domain.notification;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MailNotificationData extends AbstractNotificationData {

    private final String subject;
    private final String recipientEmailAddress;

    public MailNotificationData(String notificationMessageContent,
                                LocalDateTime scheduledAt,
                                String subject,
                                String recipientEmailAddress) {
        super(notificationMessageContent, scheduledAt);
        this.subject = subject;
        this.recipientEmailAddress = recipientEmailAddress;
    }
}
