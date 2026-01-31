package com.novacomp.notification.infrastructure.adapter.out;

import com.novacomp.notification.application.port.out.NotificationEventPublisher;
import com.novacomp.notification.domain.mapper.KafkaMapper;
import com.novacomp.notification.domain.model.Event;
import com.novacomp.notification.domain.model.Notification;
import com.novacomp.notification.domain.model.NotificationChannel;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import static com.novacomp.notification.infrastructure.utils.Constants.LOG_FAILED_TO_SEND;

@Slf4j
public class KafkaNotificationPublisher implements NotificationEventPublisher {

    private final KafkaProducer<String, String> producer;
    private final KafkaMapper kafkaMapper;
    private final String topic;

    public KafkaNotificationPublisher(KafkaProducer<String, String> producer, KafkaMapper kafkaMapper, String topic) {
        this.producer = producer;
        this.kafkaMapper = kafkaMapper;
        this.topic = topic;
    }

    @Override
    public void publish(Notification notification, NotificationChannel channel) {
        try {
            Event kafkaEvent = kafkaMapper.toKafkaEvent(notification);
            producer.send(new ProducerRecord<>(topic,  kafkaEvent.getKey(), kafkaEvent.getContent()));
        } catch (Exception e) {
            log.error(LOG_FAILED_TO_SEND, e);
        }
    }
}

