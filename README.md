# notification-core
library to send notificacion in multiply channels


Notification notification = Notification.builder()
.recipient("user@mail.com")
.channels(List.of(EMAIL, SLACK))
.build();

sender.send(notification);