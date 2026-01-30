package com.novacomp.notification.domain.validation;

import com.novacomp.notification.domain.model.Notification;

import java.util.List;

public class CompositeNotificationValidator implements NotificationValidator {

    private final List<NotificationValidator> validators;

    public CompositeNotificationValidator(List<NotificationValidator> validators) {
        this.validators = validators;
    }

    @Override
    public void validate(Notification notification) {
        for (NotificationValidator validator : validators) {
            validator.validate(notification);
        }
    }
}
