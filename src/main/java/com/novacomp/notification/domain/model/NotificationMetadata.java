package com.novacomp.notification.domain.model;

public final class NotificationMetadata {

    private NotificationMetadata() {}

    // comunes
    public static final String MESSAGE = "message";

    // email
    public static final String EMAIL_SUBJECT = "email_subject";
    public static final String EMAIL_FROM = "email_from";

    // sms
    public static final String SMS_PHONE = "sms_phone";

    // slack
    public static final String SLACK_CHANNEL = "slack_channel";

    // teams
    public static final String TEAMS_CONVERSATION = "teams_conversation";
}

