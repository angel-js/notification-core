package com.novacomp.notification.mocks;

import com.novacomp.notification.domain.model.Notification;
import com.novacomp.notification.domain.model.NotificationChannel;

import java.time.Instant;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class NotificationsMocks {

    public static Notification validEmailNotification() {
        return Notification.builder()
                .id(UUID.randomUUID())
                .recipient("user@test.com")
                .content("Hello email")
                .createdAt(Instant.now())
                .channels(Set.of(NotificationChannel.EMAIL))
                .metadata(Map.of("message", "Hola"))
                .build();
    }

    public static Notification validSlackNotification() {
        return Notification.builder()
                .id(UUID.randomUUID())
                .recipient("#general")
                .content("Slack message")
                .createdAt(Instant.now())
                .channels(Set.of(NotificationChannel.SLACK))
                .metadata(Map.of("message", "Hello Slack"))
                .build();
    }

    public static Notification validSmsNotification() {
        return Notification.builder()
                .id(UUID.randomUUID())
                .recipient("+56912345678")
                .content("SMS content")
                .createdAt(Instant.now())
                .channels(Set.of(NotificationChannel.SMS))
                .metadata(Map.of("message", "Hola SMS"))
                .build();
    }

    public static Notification validTeamsNotification() {
        return Notification.builder()
                .id(UUID.randomUUID())
                .recipient("teams-channel-123")
                .content("Teams content")
                .createdAt(Instant.now())
                .channels(Set.of(NotificationChannel.TEAMS))
                .metadata(Map.of("message", "Hello Teams"))
                .build();
    }



}
