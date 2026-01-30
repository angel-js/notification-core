package com.novacomp.notification.domain.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.novacomp.notification.domain.model.Event;
import com.novacomp.notification.domain.model.Notification;
import com.novacomp.notification.infrastructure.config.JsonMapper;

import static com.novacomp.notification.infrastructure.utils.Constants.FAILED_TO_SERIALIZE;

public class KafkaMapper {

    private final JsonMapper jsonMapper;

    public KafkaMapper(JsonMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    public Event toKafkaEvent(Notification notification){
        String jsonToSend = toJsonStr(notification);
        return Event.builder()
                .key(notification.getId().toString())
                .content(jsonToSend)
                .build();
    }

    public static String toJsonStr(Notification notification) {
        try {
            return JsonMapper.instance().writeValueAsString(notification);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(
                    FAILED_TO_SERIALIZE, e
            );
        }
    }
}
