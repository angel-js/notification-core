package com.novacomp.notification.infrastructure.adapter.out.strategy;

import com.novacomp.notification.domain.model.Notification;
import com.novacomp.notification.domain.validation.AbstractNotificationStrategy;
import com.novacomp.notification.domain.validation.CommonNotificationValidator;
import com.novacomp.notification.domain.validation.CompositeNotificationValidator;
import com.novacomp.notification.domain.validation.strategy.TeamsNotificationValidator;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

import static com.novacomp.notification.domain.model.NotificationMetadata.MESSAGE;
import static com.novacomp.notification.domain.model.NotificationMetadata.TEAMS_CONVERSATION;
import static com.novacomp.notification.domain.model.ProviderMetadata.PROVIDER_API_KEY;
import static com.novacomp.notification.domain.model.ProviderMetadata.TEAMS_WEBHOOK;

@Slf4j
public class TeamsNotificationStrategy  extends AbstractNotificationStrategy {

    public TeamsNotificationStrategy() {
        super(new CompositeNotificationValidator(
                List.of(
                        new CommonNotificationValidator(),
                        new TeamsNotificationValidator()
                )
        ));
    }

    @Override
    protected void doSend(Notification notification) {
        Map<String, String> metadata = notification.getMetadata();
        String conversation = metadata.get(TEAMS_CONVERSATION);
        String message = metadata.get(MESSAGE);
        String webHook = metadata.getOrDefault(TEAMS_WEBHOOK, "DEFAULT");

        log.info(
                "[TEAMS] conversation={} message={}",
                conversation,
                message
        );
    }
}
