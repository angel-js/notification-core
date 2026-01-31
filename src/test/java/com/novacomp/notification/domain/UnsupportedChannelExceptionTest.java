package com.novacomp.notification.domain;

import com.novacomp.notification.domain.exception.UnsupportedChannelException;
import com.novacomp.notification.domain.model.NotificationChannel;
import org.junit.jupiter.api.Test;

import static com.novacomp.notification.infrastructure.utils.Constants.NOTIFICATION_CHANNEL_MSG;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UnsupportedChannelExceptionTest {

    @Test
    void shouldStoreChannelAndBuildCorrectMessage() {
        // given
        NotificationChannel channel = NotificationChannel.EMAIL;

        // when
        UnsupportedChannelException exception =
                new UnsupportedChannelException(channel);

        // then
        assertEquals(channel, exception.getChannel());
        assertTrue(exception instanceof RuntimeException);

        assertEquals(
                NOTIFICATION_CHANNEL_MSG + channel,
                exception.getMessage()
        );
    }
}
