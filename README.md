# Notification Library

Librería Java para el envío de notificaciones multicanal con arquitectura desacoplada, extensible y orientada a eventos.
Permite enviar una notificación por múltiples canales (Email, Slack, SMS, Teams) y opcionalmente publicar eventos a Kafka (o cualquier bus) sin acoplar el dominio a la infraestructura.

### 🎯 Objetivos

Unificar el envío de notificaciones en distintos canales
Evitar acoplamiento entre dominio e infraestructura
Facilitar la extensión de nuevos canales
Permitir publicación de eventos (Kafka, Pub/Sub, etc.) sin romper estrategias existentes
Tener código altamente testeable

### 🧠 Arquitectura (high level)

La librería se basa en los siguientes patrones:
Strategy Pattern → cada canal es una estrategia
Factory + Registry → resolución dinámica de estrategias
Chain of Responsibility → validaciones por canal
Null Object Pattern → publisher opcional
Hexagonal / Clean Architecture friendly
```
├───main
│   └───java
│       └───com
│           └───novacomp
│               └───notification
│                   ├───application
│                   │   ├───port
│                   │   │   ├───in
│                   │   │   └───out
│                   │   └───service
│                   │       └───strategy
│                   ├───domain
│                   │   ├───exception
│                   │   ├───mapper
│                   │   ├───model
│                   │   └───validation
│                   │       └───strategy
│                   └───infrastructure
│                       ├───adapter
│                       │   └───out
│                       │       └───strategy
│                       ├───config
│                       └───utils
```

### 📦 Canales soportados

Actualmente la librería incluye:

Canal	Estrategia
📧 Email	EmailNotificationStrategy
💬 Slack	SlackNotificationStrategy
📱 SMS	SmsNotificationStrategy
👥 Teams	TeamsNotificationStrategy

### 👉 Cada canal tiene:

Validaciones comunes
Validaciones específicas
Lógica de envío desacoplada

### 🧾 Modelo principal
```
Notification
@Data
@Builder
public class Notification {
private UUID id;                            // ID único
private String recipient;                   // Destinatario
private String content;                     // Contenido base
private Instant createdAt;                  // Fecha creación
private Set<NotificationChannel> channels;  // Canales destino
private Map<String, String> metadata;       // Metadata por canal
}
```

### 🚀 Uso básico
1️⃣ Crear las estrategias
```
Map<NotificationChannel, NotificationStrategy> strategies = Map.of(
NotificationChannel.EMAIL, new EmailNotificationStrategy(),
NotificationChannel.SLACK, new SlackNotificationStrategy(),
NotificationChannel.SMS, new SmsNotificationStrategy(),
NotificationChannel.TEAMS, new TeamsNotificationStrategy()
);
```

2️⃣ Crear el registry y factory
```
NotificationStrategyRegistry registry =
new NotificationStrategyRegistry(strategies);

NotificationStrategyFactory factory =
new NotificationStrategyFactory(registry);
```

3️⃣ Elegir cómo publicar eventos
🔹 Sin eventos (default)
```
NotificationEventPublisher publisher =
new NoSendNotificationPublisher();
```

🔹 Con Kafka
```
KafkaProducer<String, String> producer = /* configuración Kafka */;
KafkaMapper mapper = new KafkaMapper(JsonMapper.instance());

NotificationEventPublisher publisher =
new KafkaNotificationPublisher(
producer,
mapper,
"notification-topic"
);
```

4️⃣ Crear el sender principal
```
SendNotification sender =
new NotificationSender(factory, publisher);
```

5️⃣ Enviar una notificación
```
Notification notification = Notification.builder()
.id(UUID.randomUUID())
.recipient("user@email.com")
.content("Hello!")
.createdAt(Instant.now())
.channels(Set.of(
NotificationChannel.EMAIL,
NotificationChannel.SLACK
))
.metadata(Map.of(
"message", "Hola desde la librería"
))
.build();

sender.send(notification);
```

✔ Se envía por todos los canales
✔ Se publican eventos si el publisher lo permite
✔ Un fallo en un canal no rompe los demás

### 📨 Publicación de eventos

La librería publica eventos después del envío exitoso del canal.
Evento Kafka
Key → notificationId
Value → JSON completo de la notificación
```
Ejemplo:

{
    "id": "uuid",
    "recipient": "user@email.com",
    "content": "Hello",
    "createdAt": "...",
    "channels": ["EMAIL"],
    "metadata": {
    "message": "Hola"
    }
}
```
### 🛡️ Validaciones

Validaciones comunes
Notificación no nula
Recipient requerido
Al menos un canal
Fecha de creación obligatoria
Contenido no nulo
Validaciones específicas por canal

Ejemplos:

Email → formato de email
Slack → metadata requerida
SMS → número válido
Teams → identificador de canal/contacto

### 🧩 Extensión: agregar un nuevo canal

Crear estrategia:
```
class WhatsAppNotificationStrategy extends AbstractNotificationStrategy
```

Crear validator
```
Registrar en el Map<NotificationChannel, NotificationStrategy>
```
### Listo ✅

No hay que tocar el core.

### 📌 Filosofía de diseño

Open/Closed Principle
Fail fast en validaciones
Fail safe en infraestructura
Dominio libre de Kafka
Extensible sin refactors


## Esta documentación fue generada con IA a través de una ventana de contexto

El uso de IA en este proyecto fue pensado para acelerar el proceso en el desarrollo, 
más no en la toma de decisiones, los test tambien fueron generados con IA.
Toda documentacion y test fue revisada.