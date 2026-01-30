package com.novacomp.notification.infrastructure.adapter.out.strategy;

import com.novacomp.notification.application.port.out.NotificationStrategy;
import com.novacomp.notification.domain.model.Notification;
import com.novacomp.notification.domain.validation.AbstractNotificationStrategy;
import com.novacomp.notification.domain.validation.CommonNotificationValidator;
import com.novacomp.notification.domain.validation.CompositeNotificationValidator;
import com.novacomp.notification.domain.validation.strategy.SlackNotificationValidator;
import com.novacomp.notification.domain.validation.strategy.SmsNotificationValidator;
import com.novacomp.notification.infrastructure.adapter.out.KafkaNotification;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class SmsNotificationStrategy  extends AbstractNotificationStrategy {
    private final KafkaNotification kafkaNotification;

    public SmsNotificationStrategy(KafkaNotification kafkaNotification) {
        super(new CompositeNotificationValidator(
                List.of(
                        new CommonNotificationValidator(),
                        new SmsNotificationValidator()
                )
        ));
        this.kafkaNotification = kafkaNotification;
    }


    @Override
    protected void doSend(Notification notification) {
        /// Simulación de envío
        log.info(
                "[SMS] Sending phone number to={} | message={} | metadata={}",
                notification.getRecipient(),
                notification.getMetadata().getOrDefault("message", ""),
                notification.getMetadata()
        );
        /// Envio a Kafka/ pubSub
        kafkaNotification.pushNotification(notification);
    }
}
