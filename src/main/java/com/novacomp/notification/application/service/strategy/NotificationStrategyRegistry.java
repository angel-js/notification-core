package com.novacomp.notification.application.service.strategy;

import com.novacomp.notification.application.port.out.NotificationStrategy;
import com.novacomp.notification.domain.exception.UnsupportedChannelException;
import com.novacomp.notification.domain.model.NotificationChannel;

import java.util.Map;

public class NotificationStrategyRegistry {

    private final Map<NotificationChannel, NotificationStrategy> strategies;

    public NotificationStrategyRegistry(
            Map<NotificationChannel, NotificationStrategy> strategies
    ) {
        this.strategies = Map.copyOf(strategies);
    }

    public NotificationStrategy get(NotificationChannel channel) {
        NotificationStrategy strategy = strategies.get(channel);
        if (strategy == null) {
            throw new UnsupportedChannelException(channel);
        }
        return strategy;
    }
}


