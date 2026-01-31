package com.novacomp.notification.infrastructure;

import com.novacomp.notification.domain.model.Notification;
import com.novacomp.notification.infrastructure.adapter.out.strategy.SlackNotificationStrategy;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.novacomp.notification.infrastructure.utils.Constants.CREATION_DATE_IS_REQUIRED;
import static com.novacomp.notification.infrastructure.utils.Constants.RECIPIENT_IS_REQUIRED;
import static com.novacomp.notification.mocks.NotificationsMocks.validSlackNotification;
import static org.junit.jupiter.api.Assertions.*;

class SlackNotificationStrategyTest {

    private final SlackNotificationStrategy strategy =
            new SlackNotificationStrategy();

    @Test
    void shouldFailWhenRecipientIsMissing() {
        Notification notification = validSlackNotification();
        notification.setRecipient(null);

        IllegalArgumentException ex =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> strategy.send(notification)
                );

        assertEquals(RECIPIENT_IS_REQUIRED, ex.getMessage());
    }

    @Test
    void shouldFailWhenSlackMessageIsMissing() {
        Notification notification = validSlackNotification();
        notification.setMetadata(Map.of()); // no message

        IllegalArgumentException ex =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> strategy.send(notification)
                );

        // depende de tu SlackNotificationValidator
        assertEquals("Invalid id: #general", ex.getMessage());
    }

    @Test
    void shouldFailWhenCreatedAtIsNull() {
        Notification notification = validSlackNotification();
        notification.setCreatedAt(null);

        IllegalArgumentException ex =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> strategy.send(notification)
                );

        assertEquals(CREATION_DATE_IS_REQUIRED, ex.getMessage());
    }
}

