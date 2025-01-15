package com.mba.notificationservice.insfrastructure.adapter.in.kafka;

import com.mba.notificationservice.application.usecase.SendNotificationUseCase;
import com.mba.notificationservice.domain.model.Notification;
import com.mba.notificationservice.infrastructure.adapter.in.kafka.NotificationConsumerAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

class NotificationConsumerAdapterTest {

    @Mock
    private SendNotificationUseCase sendNotificationUseCase;

    @InjectMocks
    private NotificationConsumerAdapter notificationConsumerAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListenNotification() {
        String message = "Test notification message";
        notificationConsumerAdapter.listenNotification(message);
        ArgumentCaptor<Notification> captor = ArgumentCaptor.forClass(Notification.class);
        verify(sendNotificationUseCase, times(1)).sendNotification(captor.capture());
        Notification capturedNotification = captor.getValue();
        assertEquals("Test notification message", capturedNotification.getMessage());
    }
}
