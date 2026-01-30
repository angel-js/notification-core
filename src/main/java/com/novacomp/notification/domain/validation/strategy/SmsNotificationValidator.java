package com.novacomp.notification.domain.validation.strategy;

import com.novacomp.notification.domain.model.Notification;
import com.novacomp.notification.domain.validation.NotificationValidator;

import static com.novacomp.notification.infrastructure.utils.Constants.INVALID_PHONE_NUMBER;

public class SmsNotificationValidator implements NotificationValidator {

    @Override
    public void validate(Notification notification) {
        if (!notification.getRecipient().matches("^\\+?[0-9]{8,15}$")) {
            throw new IllegalArgumentException(
                    INVALID_PHONE_NUMBER + notification.getRecipient()
            );
        }
    }
}
