package com.novacomp.notification.domain.validation;

import com.novacomp.notification.domain.model.Notification;
import lombok.extern.slf4j.Slf4j;

import static com.novacomp.notification.infrastructure.utils.Constants.*;

@Slf4j
public class CommonNotificationValidator implements NotificationValidator{

    @Override
    public void validate(Notification notification) {

        if (notification == null) {
            log.error(NOT_BE_NULL);
            throw new IllegalArgumentException(NOT_BE_NULL);
        }

        if (notification.getRecipient() == null || notification.getRecipient().isBlank()) {
            log.error(RECIPIENT_IS_REQUIRED);
            throw new IllegalArgumentException(RECIPIENT_IS_REQUIRED);
        }

        if (notification.getChannels() == null || notification.getChannels().isEmpty()) {
            log.error(AT_LEAST_ONE_CHANNEL);
            throw new IllegalArgumentException(AT_LEAST_ONE_CHANNEL);
        }

        if (notification.getCreatedAt() == null) {
            log.error(CREATION_DATE_IS_REQUIRED);
            throw new IllegalArgumentException(CREATION_DATE_IS_REQUIRED);
        }

        if (notification.getContent() == null) {
            log.error(CONTENT_CANT_NOT_BE_NULL);
            throw new IllegalArgumentException(CONTENT_CANT_NOT_BE_NULL);
        }
    }
}
