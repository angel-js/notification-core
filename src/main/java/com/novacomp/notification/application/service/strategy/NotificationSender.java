package com.novacomp.notification.application.service.strategy;

import com.novacomp.notification.application.port.in.SendNotification;
import com.novacomp.notification.domain.model.Notification;
import com.novacomp.notification.domain.model.NotificationChannel;

public class NotificationSender implements SendNotification {

    private final NotificationStrategyFactory factory;

    public NotificationSender(NotificationStrategyFactory factory) {
        this.factory = factory;
    }

    @Override
    public void send(Notification notification) {
        for (NotificationChannel channel : notification.getChannels()) {
            factory.get(channel).send(notification);
        }
    }
}
