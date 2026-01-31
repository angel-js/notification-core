package com.novacomp.notification.infrastructure.adapter.out.strategy;

import com.novacomp.notification.domain.model.Notification;
import com.novacomp.notification.domain.validation.AbstractNotificationStrategy;
import com.novacomp.notification.domain.validation.CommonNotificationValidator;
import com.novacomp.notification.domain.validation.CompositeNotificationValidator;
import com.novacomp.notification.domain.validation.strategy.SmsNotificationValidator;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

import static com.novacomp.notification.domain.model.NotificationMetadata.MESSAGE;
import static com.novacomp.notification.domain.model.NotificationMetadata.SMS_PHONE;
import static com.novacomp.notification.domain.model.ProviderMetadata.*;

@Slf4j
public class SmsNotificationStrategy  extends AbstractNotificationStrategy {

    public SmsNotificationStrategy() {
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
        String phoneNumber = metadata.get(SMS_PHONE);
        String message = metadata.getOrDefault(MESSAGE, "");
        String provider = metadata.getOrDefault(SMS_PROVIDER, "DEFAULT");
        String apiKey = metadata.getOrDefault(SMS_API_KEY, "DEFAULT");

        // Simulación realista de envío
        log.info(
                "[SMS][PROVIDER={}] Sending SMS -> phone={} | message={}",
                provider,
                phoneNumber,
                message
        );
    }

}
