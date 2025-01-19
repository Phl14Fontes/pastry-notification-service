package com.mba.notificationservice.application.port;

public interface SendNotificationPort {

    void sendNotificationToTeamsWebhook(String teamsWebhook);
}
