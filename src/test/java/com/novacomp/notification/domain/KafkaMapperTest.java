package com.novacomp.notification.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.novacomp.notification.domain.mapper.KafkaMapper;
import com.novacomp.notification.domain.model.Event;
import com.novacomp.notification.domain.model.Notification;
import com.novacomp.notification.domain.model.NotificationChannel;
import com.novacomp.notification.infrastructure.config.JsonMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.Instant;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class KafkaMapperTest {
    @Mock
    private JsonMapper ObjectMapper;
    private final KafkaMapper kafkaMapper =
            new KafkaMapper(ObjectMapper);

    @Test
    void shouldMapNotificationToKafkaEvent() {
        // given
        Notification notification = Notification.builder()
                .id(UUID.randomUUID())
                .recipient("user@test.com")
                .content("Hello Kafka")
                .createdAt(Instant.now())
                .channels(Set.of(NotificationChannel.EMAIL))
                .metadata(Map.of("source", "test"))
                .build();

        // when
        Event event = kafkaMapper.toKafkaEvent(notification);

        // then
        assertNotNull(event);
        assertEquals(notification.getId().toString(), event.getKey());

        assertNotNull(event.getContent());
        assertTrue(event.getContent().contains("Hello Kafka"));
        assertTrue(event.getContent().contains("recipient"));
    }
}

