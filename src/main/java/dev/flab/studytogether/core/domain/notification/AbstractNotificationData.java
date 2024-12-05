package dev.flab.studytogether.core.domain.notification;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public abstract class AbstractNotificationData {

    private final String notificationMessageContent;
    private final LocalDateTime scheduledAt;

    public boolean isScheduled() {
        return scheduledAt != null && scheduledAt.isAfter(LocalDateTime.now());
    }
}
