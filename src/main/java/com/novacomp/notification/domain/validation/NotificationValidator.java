package com.novacomp.notification.domain.validation;

import com.novacomp.notification.domain.model.Notification;

public interface NotificationValidator {
    void validate(Notification notification);
}
