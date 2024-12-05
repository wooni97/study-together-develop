package dev.flab.studytogether.core.domain.notification.service;

import dev.flab.studytogether.core.domain.notification.AbstractNotificationData;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NotificationService {
    private final List<NotificationSender> notificationSenders;
    private final Map<Class<? extends AbstractNotificationData>, NotificationSender> senderMap = new HashMap<>();

    public NotificationService(List<NotificationSender> notificationSenders) {
        this.notificationSenders = notificationSenders;
        initializeSenderMap();
    }

    private void initializeSenderMap() {
        for(NotificationSender notificationSender : notificationSenders) {
            senderMap.put(notificationSender.getSupportedDataTypeClass(), notificationSender);
        }
    }

    public void send(AbstractNotificationData notificationData) {
        NotificationSender notificationSender = senderMap.get(notificationData.getClass());

        if(notificationSender == null) {
            throw new IllegalArgumentException("");
        }

        notificationSender.send(notificationData);
    }
}
