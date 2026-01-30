package com.novacomp.notification.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class Notification {
    private UUID id;                            /// ID unico del mensaje
    private String recipient;                   /// Persona, Canal, Grupo, Mail etc
    private String content;                     /// Contenido del mensaje
    private Instant createdAt;                  /// Fecha de creacion
    private Set<NotificationChannel> channels;  /// canales donde seran enviados
    private Map<String, String> metadata;       /// Metadata custom para los canales
}
