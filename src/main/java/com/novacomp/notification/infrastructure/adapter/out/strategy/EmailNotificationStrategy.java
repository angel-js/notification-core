package com.novacomp.notification.infrastructure.adapter.out.strategy;

import com.novacomp.notification.domain.model.Notification;

import com.novacomp.notification.domain.validation.AbstractNotificationStrategy;
import com.novacomp.notification.domain.validation.CommonNotificationValidator;
import com.novacomp.notification.domain.validation.CompositeNotificationValidator;
import com.novacomp.notification.domain.validation.strategy.EmailNotificationValidator;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

import static com.novacomp.notification.domain.model.NotificationMetadata.EMAIL_SUBJECT;
import static com.novacomp.notification.domain.model.NotificationMetadata.MESSAGE;
import static com.novacomp.notification.domain.model.ProviderMetadata.EMAIL_API_KEY;
import static com.novacomp.notification.domain.model.ProviderMetadata.EMAIL_PROVIDER;

@Slf4j
public class EmailNotificationStrategy extends AbstractNotificationStrategy {

    public EmailNotificationStrategy() {
        super(new CompositeNotificationValidator(
                List.of(
                        new CommonNotificationValidator(),
                        new EmailNotificationValidator()
                )
        ));
    }

    @Override
    protected void doSend(Notification notification) {
        String to = notification.getRecipient();
        Map<String, String> metadata = notification.getMetadata();
        String subject = metadata.getOrDefault(EMAIL_SUBJECT, "No Subject");
        String message = metadata.getOrDefault(MESSAGE, "");
        String provider = metadata.getOrDefault(EMAIL_PROVIDER, "DEFAULT");
        String apiKey = metadata.getOrDefault(EMAIL_API_KEY, "DEFAULT");

        // Simulación realista de envío
        log.info(
                "[EMAIL][PROVIDER={}] Sending email -> to={} | subject={} | body={} ",
                provider,
                to,
                subject,
                message
        );
    }

}

