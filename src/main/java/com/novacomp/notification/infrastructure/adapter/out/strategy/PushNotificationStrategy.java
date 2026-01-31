package com.novacomp.notification.infrastructure.adapter.out.strategy;

import com.novacomp.notification.domain.model.Notification;
import com.novacomp.notification.domain.validation.AbstractNotificationStrategy;
import com.novacomp.notification.domain.validation.CommonNotificationValidator;
import com.novacomp.notification.domain.validation.CompositeNotificationValidator;
import com.novacomp.notification.domain.validation.NotificationValidator;
import com.novacomp.notification.domain.validation.strategy.SmsNotificationValidator;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

import static com.novacomp.notification.domain.model.NotificationMetadata.*;
import static com.novacomp.notification.domain.model.NotificationMetadata.SMS_PHONE;
import static com.novacomp.notification.domain.model.ProviderMetadata.*;

@Slf4j
public class PushNotificationStrategy extends AbstractNotificationStrategy {

    protected PushNotificationStrategy(NotificationValidator validator) {
        super(new CompositeNotificationValidator(
                List.of(
                        new CommonNotificationValidator(),
                        new SmsNotificationValidator()
                )
        ));
    }

    @Override
    protected void doSend(Notification notification) {
        Map<String, String> metadata = notification.getMetadata();
        String phone = metadata.getOrDefault(SMS_PHONE, "No Subject");
        String message = metadata.getOrDefault(MESSAGE, "");
        String apiKey = metadata.getOrDefault(PROVIDER_API_KEY, "DEFAULT");

        log.info(
                "[PUSH] phone={} message={}",
                phone,
                message
        );
    }
}
