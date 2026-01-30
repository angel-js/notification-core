package com.novacomp.notification.domain.exception;

import com.novacomp.notification.domain.model.NotificationChannel;
import lombok.Getter;

import static com.novacomp.notification.infrastructure.utils.Constants.NOTIFICATION_CHANNEL_MSG;

@Getter
public class UnsupportedChannelException extends RuntimeException {

    private final NotificationChannel channel;


    public UnsupportedChannelException(NotificationChannel channel) {
        super(buildMessage(channel));
        this.channel = channel;
    }

    private static String buildMessage(NotificationChannel channel) {
        return NOTIFICATION_CHANNEL_MSG + channel;
    }
}

