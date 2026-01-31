package com.novacomp.notification.application;

import com.novacomp.notification.application.port.out.NotificationStrategy;
import com.novacomp.notification.application.service.strategy.NotificationStrategyFactory;
import com.novacomp.notification.application.service.strategy.NotificationStrategyRegistry;
import com.novacomp.notification.domain.exception.UnsupportedChannelException;
import com.novacomp.notification.domain.model.NotificationChannel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NotificationStrategyFactoryTest {

    @Mock
    NotificationStrategyRegistry registry;

    @Mock
    NotificationStrategy strategy;

    NotificationStrategyFactory factory;

    @BeforeEach
    void setUp() {
        factory = new NotificationStrategyFactory(registry);
    }

    @Test
    void shouldReturnStrategyFromRegistry() {
        // given
        NotificationChannel channel = NotificationChannel.EMAIL;
        when(registry.get(channel)).thenReturn(strategy);

        // when
        NotificationStrategy result = factory.get(channel);

        // then
        assertSame(strategy, result);
        verify(registry).get(channel);
    }

    @Test
    void shouldPropagateExceptionWhenChannelIsNotSupported() {
        // given
        NotificationChannel channel = NotificationChannel.SMS;
        UnsupportedChannelException exception =
                new UnsupportedChannelException(channel);

        when(registry.get(channel)).thenThrow(exception);

        // when & then
        UnsupportedChannelException thrown =
                assertThrows(
                        UnsupportedChannelException.class,
                        () -> factory.get(channel)
                );

        assertSame(exception, thrown);
        verify(registry).get(channel);
    }
}

