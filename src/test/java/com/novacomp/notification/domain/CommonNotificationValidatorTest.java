package com.novacomp.notification.domain;

import com.novacomp.notification.domain.model.Notification;
import com.novacomp.notification.domain.model.NotificationChannel;
import com.novacomp.notification.domain.validation.CommonNotificationValidator;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

import static com.novacomp.notification.infrastructure.utils.Constants.*;
import static org.junit.jupiter.api.Assertions.*;

class CommonNotificationValidatorTest {

    private final CommonNotificationValidator validator =
            new CommonNotificationValidator();

    @Test
    void shouldPassValidationForValidNotification() {
        // given
        Notification notification = validNotification();

        // when / then
        assertDoesNotThrow(() -> validator.validate(notification));
    }
    @Test
    void shouldThrowWhenNotificationIsNull() {
        // when
        IllegalArgumentException ex =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> validator.validate(null)
                );

        // then
        assertEquals(NOT_BE_NULL, ex.getMessage());
    }
    @Test
    void shouldThrowWhenRecipientIsNull() {
        Notification notification = validNotification();
        notification.setRecipient(null);

        IllegalArgumentException ex =
                assertThrows(IllegalArgumentException.class,
                        () -> validator.validate(notification));

        assertEquals(RECIPIENT_IS_REQUIRED, ex.getMessage());
    }

    @Test
    void shouldThrowWhenRecipientIsBlank() {
        Notification notification = validNotification();
        notification.setRecipient("   ");

        IllegalArgumentException ex =
                assertThrows(IllegalArgumentException.class,
                        () -> validator.validate(notification));

        assertEquals(RECIPIENT_IS_REQUIRED, ex.getMessage());
    }

    @Test
    void shouldThrowWhenChannelsAreNull() {
        Notification notification = validNotification();
        notification.setChannels(null);

        IllegalArgumentException ex =
                assertThrows(IllegalArgumentException.class,
                        () -> validator.validate(notification));

        assertEquals(AT_LEAST_ONE_CHANNEL, ex.getMessage());
    }

    @Test
    void shouldThrowWhenChannelsAreEmpty() {
        Notification notification = validNotification();
        notification.setChannels(Set.of());

        IllegalArgumentException ex =
                assertThrows(IllegalArgumentException.class,
                        () -> validator.validate(notification));

        assertEquals(AT_LEAST_ONE_CHANNEL, ex.getMessage());
    }

    @Test
    void shouldThrowWhenCreatedAtIsNull() {
        Notification notification = validNotification();
        notification.setCreatedAt(null);

        IllegalArgumentException ex =
                assertThrows(IllegalArgumentException.class,
                        () -> validator.validate(notification));

        assertEquals(CREATION_DATE_IS_REQUIRED, ex.getMessage());
    }

    @Test
    void shouldThrowWhenContentIsNull() {
        Notification notification = validNotification();
        notification.setContent(null);

        IllegalArgumentException ex =
                assertThrows(IllegalArgumentException.class,
                        () -> validator.validate(notification));

        assertEquals(CONTENT_CANT_NOT_BE_NULL, ex.getMessage());
    }

    private Notification validNotification() {
        return Notification.builder()
                .id(UUID.randomUUID())
                .recipient("user@test.com")
                .content("Hello")
                .createdAt(Instant.now())
                .channels(Set.of(NotificationChannel.EMAIL))
                .build();
    }
}
