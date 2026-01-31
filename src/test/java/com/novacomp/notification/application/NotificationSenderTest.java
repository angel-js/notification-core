package com.novacomp.notification.application;

import com.novacomp.notification.application.port.out.NotificationEventPublisher;
import com.novacomp.notification.application.port.out.NotificationStrategy;
import com.novacomp.notification.application.service.strategy.NotificationSender;
import com.novacomp.notification.application.service.strategy.NotificationStrategyFactory;
import com.novacomp.notification.domain.model.Notification;
import com.novacomp.notification.domain.model.NotificationChannel;
import com.novacomp.notification.infrastructure.adapter.out.NoSendNotificationPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationSenderTest {

    @Mock
    NotificationStrategyFactory factory;

    @Mock
    NotificationStrategy emailStrategy;

    @Mock
    NotificationStrategy smsStrategy;

    @Mock
    NotificationEventPublisher eventPublisher;

    NotificationSender sender;

    @BeforeEach
    void setUp() {
        sender = new NotificationSender(factory, eventPublisher);
    }

    @Test
    void shouldSendNotificationAndPublishEventForEachChannel() {
        // given
        Notification notification = Notification.builder()
                .id(UUID.randomUUID())
                .content("Hello")
                .channels(Set.of(NotificationChannel.EMAIL, NotificationChannel.SMS))
                .build();

        when(factory.get(NotificationChannel.EMAIL)).thenReturn(emailStrategy);
        when(factory.get(NotificationChannel.SMS)).thenReturn(smsStrategy);

        // when
        sender.send(notification);

        // then
        verify(emailStrategy).send(notification);
        verify(smsStrategy).send(notification);

        verify(eventPublisher)
                .publish(notification, NotificationChannel.EMAIL);
        verify(eventPublisher)
                .publish(notification, NotificationChannel.SMS);
    }

    @Test
    void shouldContinueWhenOneChannelFails() {
        // given
        Notification notification = Notification.builder()
                .channels(Set.of(NotificationChannel.EMAIL, NotificationChannel.SMS))
                .build();

        when(factory.get(NotificationChannel.EMAIL)).thenReturn(emailStrategy);
        when(factory.get(NotificationChannel.SMS)).thenReturn(smsStrategy);

        doThrow(new RuntimeException("SMTP down"))
                .when(emailStrategy).send(notification);

        // when
        sender.send(notification);

        // then
        verify(emailStrategy).send(notification);
        verify(smsStrategy).send(notification);

        verify(eventPublisher)
                .publish(notification, NotificationChannel.SMS);

        // EMAIL publish NO garantiza nada porque falló antes
    }

    @Test
    void shouldSendNotificationWithoutFailingWhenNoOpPublisherIsUsed() {
        // given
        NotificationEventPublisher noOpPublisher =
                new NoSendNotificationPublisher();

        NotificationSender sender =
                new NotificationSender(factory, noOpPublisher);

        Notification notification = Notification.builder()
                .channels(Set.of(NotificationChannel.EMAIL))
                .build();

        when(factory.get(NotificationChannel.EMAIL)).thenReturn(emailStrategy);

        // when
        sender.send(notification);

        // then
        verify(emailStrategy).send(notification);
        // no asserts de eventos → caja negra
    }
}

