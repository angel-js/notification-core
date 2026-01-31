package com.novacomp.notification.application.service.strategy;

import com.novacomp.notification.application.port.in.SendNotification;
import com.novacomp.notification.application.port.out.NotificationEventPublisher;
import com.novacomp.notification.domain.model.Notification;
import com.novacomp.notification.domain.model.NotificationChannel;
import lombok.extern.slf4j.Slf4j;
import static com.novacomp.notification.infrastructure.utils.Constants.LOG_CHANNEL_FAILED;

@Slf4j
public class NotificationSender implements SendNotification {

    private final NotificationStrategyFactory factory;
    private final NotificationEventPublisher eventPublisher;

    public NotificationSender(NotificationStrategyFactory factory, NotificationEventPublisher eventPublisher) {
        this.factory = factory;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void send(Notification notification) {
        for (NotificationChannel channel : notification.getChannels()) {
            try {
                factory.get(channel).send(notification);
                eventPublisher.publish(notification, channel);
            } catch (Exception e) {
                log.error(LOG_CHANNEL_FAILED, channel, e);
            }
        }
    }
}
