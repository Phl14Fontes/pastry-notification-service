package com.mba.notificationservice.infrastructure.adapter.in.kafka;

import com.mba.notificationservice.application.port.NotificationConsumerPort;
import com.mba.notificationservice.application.usecase.SendNotificationUseCase;
import com.mba.notificationservice.domain.model.Notification;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationConsumerAdapter implements NotificationConsumerPort {

    private final SendNotificationUseCase sendNotificationUseCase;

    private static final Logger log = LoggerFactory.getLogger(NotificationConsumerAdapter.class);

    @KafkaListener(topics = "success-notification", groupId = "notification-service")
    @Override
    public void listenNotification(String message) {
        var notification = Notification.builder()
                .message(message)
                .build();
        sendNotificationUseCase.sendNotification(notification);
    }

}