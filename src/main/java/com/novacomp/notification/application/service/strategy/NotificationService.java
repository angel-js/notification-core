package com.novacomp.notification.application.service.strategy;

import com.novacomp.notification.application.port.in.SendNotification;
import com.novacomp.notification.application.port.out.NotificationStrategy;
import com.novacomp.notification.domain.model.Notification;
import com.novacomp.notification.domain.model.NotificationChannel;
import lombok.extern.slf4j.Slf4j;

import static com.novacomp.notification.infrastructure.utils.Constants.CHANNEL_FAILED;

@Slf4j
public class NotificationService implements SendNotification {

    private final NotificationStrategyFactory factory;

    public NotificationService(NotificationStrategyFactory factory) {
        this.factory = factory;
    }

    @Override
    public void send(Notification notification) {
        for (NotificationChannel channel : notification.getChannels()) {
            try {
                factory.get(channel).send(notification);
            } catch (Exception e) {
                log.error(CHANNEL_FAILED, channel, e);
            }
        }
    }
}
