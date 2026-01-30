package com.novacomp.notification.application.port.out;

import com.novacomp.notification.domain.model.Notification;

public interface PushEventNotification {
    void pushNotification(Notification notification);
}
