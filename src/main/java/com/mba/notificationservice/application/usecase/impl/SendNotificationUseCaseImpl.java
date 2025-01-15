package com.mba.notificationservice.application.usecase.impl;

import com.mba.notificationservice.application.port.SendNotificationPort;
import com.mba.notificationservice.application.usecase.SendNotificationUseCase;
import com.mba.notificationservice.domain.model.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendNotificationUseCaseImpl implements SendNotificationUseCase {

    private final SendNotificationPort sendNotificationPort;

    @Override
    public void sendNotification(Notification notification) {
        System.out.println("Notification will be sent to teams webhook");
        sendNotificationPort.sendNotificationToTeamsWebhook(notification.getMessage());
    }
}
