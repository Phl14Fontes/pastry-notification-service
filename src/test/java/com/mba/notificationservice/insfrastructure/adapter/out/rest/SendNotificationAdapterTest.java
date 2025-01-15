package com.mba.notificationservice.insfrastructure.adapter.out.rest;

import com.mba.notificationservice.infrastructure.adapter.out.rest.SendNotificationAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SendNotificationAdapterTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private SendNotificationAdapter sendNotificationAdapter;
    private String webhookUrl = "http://teste.com";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(sendNotificationAdapter, "webhookUrl", "http://teste.com");
    }

    @Test
    void testSendNotificationToTeamsWebhookSuccess() {
        ResponseEntity<String> responseEntity = new ResponseEntity<>("Success", HttpStatus.OK);
        when(restTemplate.exchange(eq(webhookUrl), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class)))
                .thenReturn(responseEntity);
        sendNotificationAdapter.sendNotificationToTeamsWebhook("Test message");
        verify(restTemplate, times(1)).exchange(eq(webhookUrl), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class));
    }

    @Test
    void testSendNotificationToTeamsWebhookFailure() {
        ResponseEntity<String> responseEntity = new ResponseEntity<>("Failure", HttpStatus.BAD_REQUEST);
        when(restTemplate.exchange(eq(webhookUrl), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class)))
                .thenReturn(responseEntity);
        sendNotificationAdapter.sendNotificationToTeamsWebhook("Test message");
        verify(restTemplate, times(1)).exchange(eq(webhookUrl), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class));
    }

    @Test
    void testSendNotificationToTeamsWebhookException() {
        HttpStatusCodeException exception = mock(HttpStatusCodeException.class);
        when(restTemplate.exchange(eq(webhookUrl), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class)))
                .thenThrow(exception);
        sendNotificationAdapter.sendNotificationToTeamsWebhook("Test message");
        verify(restTemplate, times(1)).exchange(eq(webhookUrl), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class));
    }
}
