package com.mba.notificationservice.application.usecase;

import com.mba.notificationservice.domain.model.Notification;

public interface SendNotificationUseCase {
    void sendNotification(Notification notification);
}
