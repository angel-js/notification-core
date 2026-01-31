package com.novacomp.notification.infrastructure;

import com.novacomp.notification.domain.mapper.KafkaMapper;
import com.novacomp.notification.domain.model.Event;
import com.novacomp.notification.domain.model.Notification;
import com.novacomp.notification.domain.model.NotificationChannel;
import com.novacomp.notification.infrastructure.adapter.out.KafkaNotificationPublisher;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KafkaNotificationPublisherTest {

    @Mock
    KafkaProducer<String, String> producer;

    @Mock
    KafkaMapper kafkaMapper;

    KafkaNotificationPublisher publisher;

    private static final String TOPIC = "notification-topic";

    @BeforeEach
    void setUp() {
        publisher = new KafkaNotificationPublisher(
                producer,
                kafkaMapper,
                TOPIC
        );
    }

    @Test
    void shouldPublishKafkaEventWhenNotificationIsValid() {
        // given
        Notification notification = Notification.builder()
                .id(UUID.randomUUID())
                .build();

        Event event = Event.builder()
                .key(notification.getId().toString())
                .content("{\"message\":\"hello\"}")
                .build();

        when(kafkaMapper.toKafkaEvent(notification)).thenReturn(event);

        // when
        publisher.publish(notification, NotificationChannel.EMAIL);

        // then
        ArgumentCaptor<ProducerRecord<String, String>> recordCaptor =
                ArgumentCaptor.forClass(ProducerRecord.class);

        verify(producer).send(recordCaptor.capture());

        ProducerRecord<String, String> record = recordCaptor.getValue();

        assertEquals(TOPIC, record.topic());
        assertEquals(event.getKey(), record.key());
        assertEquals(event.getContent(), record.value());
    }
}

