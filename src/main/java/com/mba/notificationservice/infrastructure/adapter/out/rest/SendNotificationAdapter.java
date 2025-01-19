package com.mba.notificationservice.infrastructure.adapter.out.rest;

import com.mba.notificationservice.application.port.SendNotificationPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Component
public class SendNotificationAdapter implements SendNotificationPort {

    @Value(value = "${teams.webhook.url}")
    private String webhookUrl;

    RestTemplate restTemplate = new RestTemplate();

    @Override
    public void sendNotificationToTeamsWebhook(String teamsWebhook) {
        var body = new HashMap<String, String>();
        body.put("@type", "MessageCard" );
        body.put("@context", "http://schema.org/extensions" );
        body.put("text", "O pedido está pronto");
        var headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        var entity = new HttpEntity<>(body, headers);

        try {
            var response = restTemplate.exchange(
                    webhookUrl,
                    HttpMethod.POST,
                    entity,
                    String.class
            );
            if (response != null && response.getStatusCodeValue() == 200) {
                System.out.println("Notification sent successfully!");
            } else if (response != null) {
                System.out.println("Failed to send notification. Response code: " + response.getStatusCodeValue());
            }
        } catch (HttpStatusCodeException e) {
            System.out.println("Exception during sending notification: " + e.getMessage());
        }
    }
}
