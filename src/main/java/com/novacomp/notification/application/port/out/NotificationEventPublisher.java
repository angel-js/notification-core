package com.novacomp.notification.application.port.out;

import com.novacomp.notification.domain.model.Notification;
import com.novacomp.notification.domain.model.NotificationChannel;

public interface NotificationEventPublisher {
    void publish(Notification notification, NotificationChannel channel);
}

