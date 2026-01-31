package com.novacomp.notification.infrastructure;

import com.novacomp.notification.domain.model.Notification;
import com.novacomp.notification.infrastructure.adapter.out.strategy.TeamsNotificationStrategy;
import org.junit.jupiter.api.Test;
import static com.novacomp.notification.infrastructure.utils.Constants.CREATION_DATE_IS_REQUIRED;
import static com.novacomp.notification.infrastructure.utils.Constants.RECIPIENT_IS_REQUIRED;
import static com.novacomp.notification.mocks.NotificationsMocks.validTeamsNotification;
import static org.junit.jupiter.api.Assertions.*;

class TeamsNotificationStrategyTest {

    private final TeamsNotificationStrategy strategy =
            new TeamsNotificationStrategy();

    @Test
    void shouldSendTeamsNotificationWhenValid() {
        // given
        Notification notification = validTeamsNotification();

        // when / then
        assertDoesNotThrow(() -> strategy.send(notification));
    }

    @Test
    void shouldFailWhenRecipientIsMissing() {
        Notification notification = validTeamsNotification();
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
        Notification notification = validTeamsNotification();
        notification.setCreatedAt(null);

        IllegalArgumentException ex =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> strategy.send(notification)
                );

        assertEquals(CREATION_DATE_IS_REQUIRED, ex.getMessage());
    }
}
