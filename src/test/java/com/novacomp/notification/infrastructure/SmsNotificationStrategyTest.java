package com.novacomp.notification.infrastructure;

import com.novacomp.notification.domain.model.Notification;
import com.novacomp.notification.infrastructure.adapter.out.strategy.SmsNotificationStrategy;
import org.junit.jupiter.api.Test;


import static com.novacomp.notification.infrastructure.utils.Constants.CREATION_DATE_IS_REQUIRED;
import static com.novacomp.notification.infrastructure.utils.Constants.RECIPIENT_IS_REQUIRED;
import static com.novacomp.notification.mocks.NotificationsMocks.validSmsNotification;
import static org.junit.jupiter.api.Assertions.*;

class SmsNotificationStrategyTest {

    private final SmsNotificationStrategy strategy =
            new SmsNotificationStrategy();

    @Test
    void shouldSendSmsWhenNotificationIsValid() {
        // given
        Notification notification = validSmsNotification();

        // when / then
        assertDoesNotThrow(() -> strategy.send(notification));
    }

    @Test
    void shouldFailWhenRecipientIsMissing() {
        Notification notification = validSmsNotification();
        notification.setRecipient(null);

        IllegalArgumentException ex =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> strategy.send(notification)
                );

        assertEquals(RECIPIENT_IS_REQUIRED, ex.getMessage());
    }
    @Test
    void shouldFailWhenCreatedAtIsNull() {
        Notification notification = validSmsNotification();
        notification.setCreatedAt(null);

        IllegalArgumentException ex =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> strategy.send(notification)
                );

        assertEquals(CREATION_DATE_IS_REQUIRED, ex.getMessage());
    }
}

