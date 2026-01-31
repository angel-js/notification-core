package com.novacomp.notification.infrastructure.adapter.out;

import com.novacomp.notification.application.port.out.NotificationEventPublisher;
import com.novacomp.notification.domain.model.Notification;
import com.novacomp.notification.domain.model.NotificationChannel;

public class NoSendNotificationPublisher implements NotificationEventPublisher {
    @Override
    public void publish(Notification notification, NotificationChannel channel) {
    }
}
