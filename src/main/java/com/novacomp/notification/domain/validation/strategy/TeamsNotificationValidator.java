package com.novacomp.notification.domain.validation.strategy;

import com.novacomp.notification.domain.model.Notification;
import com.novacomp.notification.domain.validation.NotificationValidator;

import static com.novacomp.notification.infrastructure.utils.Constants.INVALID_CHANNEL;

public class TeamsNotificationValidator implements NotificationValidator {

    @Override
    public void validate(Notification notification) {
        if (notification.getMetadata() == null) {
            throw new IllegalArgumentException(
                    INVALID_CHANNEL + notification.getRecipient()
            );
        }
    }
}
