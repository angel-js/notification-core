package com.novacomp.notification.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Event {
    private String key;
    private String content;
}
