package com.novacomp.notification.application;

import com.novacomp.notification.application.port.out.NotificationStrategy;
import com.novacomp.notification.application.service.strategy.NotificationStrategyRegistry;
import com.novacomp.notification.domain.exception.UnsupportedChannelException;
import com.novacomp.notification.domain.model.NotificationChannel;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class NotificationStrategyRegistryTest {

    @Test
    void shouldReturnStrategyForSupportedChannel() {
        // given
        NotificationStrategy emailStrategy = mock(NotificationStrategy.class);

        Map<NotificationChannel, NotificationStrategy> strategies =
                Map.of(NotificationChannel.EMAIL, emailStrategy);

        NotificationStrategyRegistry registry =
                new NotificationStrategyRegistry(strategies);

        // when
        NotificationStrategy result =
                registry.get(NotificationChannel.EMAIL);

        // then
        assertSame(emailStrategy, result);
    }

    @Test
    void shouldThrowExceptionForUnsupportedChannel() {
        // given
        NotificationStrategy emailStrategy = mock(NotificationStrategy.class);

        Map<NotificationChannel, NotificationStrategy> strategies =
                Map.of(NotificationChannel.EMAIL, emailStrategy);

        NotificationStrategyRegistry registry =
                new NotificationStrategyRegistry(strategies);

        // when & then
        UnsupportedChannelException exception =
                assertThrows(
                        UnsupportedChannelException.class,
                        () -> registry.get(NotificationChannel.SMS)
                );

        assertEquals(NotificationChannel.SMS, exception.getChannel());
    }
}

