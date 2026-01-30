package com.novacomp.notification.infrastructure.adapter.out;

import com.novacomp.notification.application.port.out.PushEventNotification;
import com.novacomp.notification.domain.mapper.KafkaMapper;
import com.novacomp.notification.domain.model.Event;
import com.novacomp.notification.domain.model.Notification;
import lombok.extern.slf4j.Slf4j;

import static com.novacomp.notification.infrastructure.utils.Constants.*;

@Slf4j
public class KafkaNotification implements PushEventNotification {

    private final KafkaMapper kafkaMapper;

    public KafkaNotification(KafkaMapper kafkaMapper) {
        this.kafkaMapper = kafkaMapper;
    }

    @Override
    public void pushNotification(Notification notification) {
        Event kafkaEvent = kafkaMapper.toKafkaEvent(notification);
        log.info(SEND_KAKFA_MOCK_MSG);
        log.info(EVENT_ID, kafkaEvent.getKey());
        log.info(EVENT_TYPE, kafkaEvent.getContent());
        log.info(SEND_KAKFA_MOCK_MSG_SUCCESSFULLY);
    }
}
