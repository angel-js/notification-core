package com.novacomp.notification.infrastructure.adapter.out.strategy;

import com.novacomp.notification.domain.model.Notification;

import com.novacomp.notification.domain.validation.AbstractNotificationStrategy;
import com.novacomp.notification.domain.validation.CommonNotificationValidator;
import com.novacomp.notification.domain.validation.CompositeNotificationValidator;
import com.novacomp.notification.domain.validation.strategy.EmailNotificationValidator;
import com.novacomp.notification.infrastructure.adapter.out.KafkaNotification;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class EmailNotificationStrategy extends AbstractNotificationStrategy {

    private final KafkaNotification kafkaNotification;

    public EmailNotificationStrategy(KafkaNotification kafkaNotification) {
        super(new CompositeNotificationValidator(
                List.of(
                        new CommonNotificationValidator(),
                        new EmailNotificationValidator()
                )
        ));
        this.kafkaNotification = kafkaNotification;
    }

    @Override
    public void doSend(Notification notification) {

        /// Simulación de envío
        log.info(
                "[EMAIL] Sending email to={} | message={} | metadata={}",
                notification.getRecipient(),
                notification.getMetadata().getOrDefault("message", ""),
                notification.getMetadata()
        );
        /// Envio a Kafka/ pubSub
        kafkaNotification.pushNotification(notification);
    }
}

