package com.mba.notificationservice.application.port;

public interface NotificationConsumerPort {

    void listenNotification(String message);
}
