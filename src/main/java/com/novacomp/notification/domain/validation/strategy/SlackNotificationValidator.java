package com.novacomp.notification.domain.validation.strategy;

import com.novacomp.notification.domain.model.Notification;
import com.novacomp.notification.domain.validation.NotificationValidator;

import static com.novacomp.notification.infrastructure.utils.Constants.INVALID_EMAIL;
import static com.novacomp.notification.infrastructure.utils.Constants.INVALID_ID;

public class SlackNotificationValidator implements NotificationValidator {
    @Override
    public void validate(Notification notification) {

        String recipient = notification.getRecipient();

        if (!recipient.contains("id")) {
            throw new IllegalArgumentException(
                    INVALID_ID + recipient
            );
        }
    }
}
