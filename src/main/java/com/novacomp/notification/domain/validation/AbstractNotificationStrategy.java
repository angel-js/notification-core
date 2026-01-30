package com.novacomp.notification.domain.validation;

import com.novacomp.notification.application.port.out.NotificationStrategy;
import com.novacomp.notification.domain.model.Notification;

public abstract class AbstractNotificationStrategy
        implements NotificationStrategy {

    private final NotificationValidator validator;

    protected AbstractNotificationStrategy(NotificationValidator validator) {
        this.validator = validator;
    }

    @Override
    public final void send(Notification notification) {
        validator.validate(notification);
        doSend(notification);
    }

    protected abstract void doSend(Notification notification);
}