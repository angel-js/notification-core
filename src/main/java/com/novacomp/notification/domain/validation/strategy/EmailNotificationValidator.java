package com.novacomp.notification.domain.validation.strategy;

import com.novacomp.notification.domain.model.Notification;
import com.novacomp.notification.domain.validation.NotificationValidator;

import static com.novacomp.notification.infrastructure.utils.Constants.INVALID_EMAIL;

public class EmailNotificationValidator implements NotificationValidator {
    @Override
    public void validate(Notification notification) {

        String recipient = notification.getRecipient();

        if (!recipient.contains("@")) {
            throw new IllegalArgumentException(
                    INVALID_EMAIL + recipient
            );
        }
    }
}
