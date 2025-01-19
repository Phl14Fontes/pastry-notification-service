package com.mba.notificationservice.application.usecase.impl;

import com.mba.notificationservice.application.port.SendNotificationPort;
import com.mba.notificationservice.domain.model.Notification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

class SendNotificationUseCaseImplTest {

    @Mock
    private SendNotificationPort sendNotificationPort;

    @InjectMocks
    private SendNotificationUseCaseImpl sendNotificationUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendNotification() {
        var notification = new Notification("Test message");
        sendNotificationUseCase.sendNotification(notification);
        verify(sendNotificationPort, times(1)).sendNotificationToTeamsWebhook("Test message");
    }
}
