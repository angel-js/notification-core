package com.novacomp.notification.infrastructure;

import com.novacomp.notification.domain.model.Notification;
import com.novacomp.notification.infrastructure.adapter.out.strategy.EmailNotificationStrategy;
import org.junit.jupiter.api.Test;

import static com.novacomp.notification.infrastructure.utils.Constants.*;
import static com.novacomp.notification.mocks.NotificationsMocks.validEmailNotification;
import static org.junit.jupiter.api.Assertions.*;

class EmailNotificationStrategyTest {

    private final EmailNotificationStrategy strategy =
            new EmailNotificationStrategy();

    @Test
    void shouldSendEmailWhenNotificationIsValid() {
        // given
        Notification notification = validEmailNotification();

        // when / then
        assertDoesNotThrow(() -> strategy.send(notification));
    }

    @Test
    void shouldFailWhenRecipientIsMissing() {
        Notification notification = validEmailNotification();
        notification.setRecipient(null);

        IllegalArgumentException ex =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> strategy.send(notification)
                );

        assertEquals(RECIPIENT_IS_REQUIRED, ex.getMessage());
    }


    @Test
    void shouldFailWhenEmailIsInvalid() {
        Notification notification = validEmailNotification();
        notification.setRecipient("invalid-email");

        IllegalArgumentException ex =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> strategy.send(notification)
                );

        // el mensaje exacto depende de tu validator
        assertEquals(INVALID_EMAIL + "invalid-email", ex.getMessage());
    }

    @Test
    void shouldFailWhenContentIsNull() {
        Notification notification = validEmailNotification();
        notification.setContent(null);

        IllegalArgumentException ex =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> strategy.send(notification)
                );

        assertEquals(CONTENT_CANT_NOT_BE_NULL, ex.getMessage());
    }


}

