package com.mba.notificationservice.application.usecase;

import com.mba.notificationservice.domain.model.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class SendNotificationUseCaseImpl implements SendNotificationUseCase {

    @Value(value = "${teams.webhook.address}")
    private String webhookUrl;

    RestTemplate restTemplate = new RestTemplate();

    @Override
    public void sendNotification(Notification notification) {

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
            if (response.getStatusCodeValue() == 200) {
                System.out.println("Mensagem enviada com sucesso!");
            } else {
                System.out.println("Falha ao enviar a mensagem. Código de resposta: " + response.getStatusCodeValue());
            }
        } catch (HttpStatusCodeException e) {
            e.getMessage();
        }
    }
}
