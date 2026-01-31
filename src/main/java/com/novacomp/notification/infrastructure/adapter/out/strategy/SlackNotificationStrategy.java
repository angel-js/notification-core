package com.novacomp.notification.infrastructure.adapter.out.strategy;

import com.novacomp.notification.domain.model.Notification;
import com.novacomp.notification.domain.validation.AbstractNotificationStrategy;
import com.novacomp.notification.domain.validation.CommonNotificationValidator;
import com.novacomp.notification.domain.validation.CompositeNotificationValidator;
import com.novacomp.notification.domain.validation.strategy.SlackNotificationValidator;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

import static com.novacomp.notification.domain.model.NotificationMetadata.MESSAGE;
import static com.novacomp.notification.domain.model.NotificationMetadata.SLACK_CHANNEL;
import static com.novacomp.notification.domain.model.ProviderMetadata.PROVIDER_API_KEY;
import static com.novacomp.notification.domain.model.ProviderMetadata.SLACK_TOKEN;

@Slf4j
public class SlackNotificationStrategy extends AbstractNotificationStrategy {

    public SlackNotificationStrategy() {
        super(new CompositeNotificationValidator(
                List.of(
                        new CommonNotificationValidator(),
                        new SlackNotificationValidator()
                )
        ));
    }

    @Override
    protected void doSend(Notification notification) {
        Map<String, String> metadata = notification.getMetadata();
        String channel = metadata.get(SLACK_CHANNEL);
        String message = metadata.get(MESSAGE);
        String token = metadata.getOrDefault(SLACK_TOKEN, "DEFAULT");

        log.info(
                "[SLACK] channel={} message={}",
                channel,
                message
        );
    }
}
