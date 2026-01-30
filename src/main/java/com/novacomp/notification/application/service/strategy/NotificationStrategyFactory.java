package com.novacomp.notification.application.service.strategy;

import com.novacomp.notification.application.port.out.NotificationStrategy;
import com.novacomp.notification.domain.model.NotificationChannel;

public class NotificationStrategyFactory {

    private final NotificationStrategyRegistry registry;

    public NotificationStrategyFactory(NotificationStrategyRegistry registry) {
        this.registry = registry;
    }

    public NotificationStrategy get(NotificationChannel channel) {
        return registry.get(channel);
    }
}
