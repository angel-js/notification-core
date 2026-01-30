package com.novacomp.notification.application.port.in;

import com.novacomp.notification.domain.model.Notification;

public interface SendNotification {
    void send(Notification notification);
}
