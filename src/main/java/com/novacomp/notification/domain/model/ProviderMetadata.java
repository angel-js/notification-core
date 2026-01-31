package com.novacomp.notification.domain.model;

public final class ProviderMetadata {

    private ProviderMetadata() {}

    // ========= COMMON =========
    public static final String PROVIDER_NAME = "provider.name";
    public static final String PROVIDER_API_KEY = "provider.apiKey";
    public static final String PROVIDER_TOKEN = "provider.token";
    public static final String PROVIDER_ENDPOINT = "provider.endpoint";

    // ========= EMAIL =========
    public static final String EMAIL_PROVIDER = "email.provider";
    public static final String EMAIL_API_KEY = "email.apiKey";
    public static final String EMAIL_FROM = "email.from";
    public static final String EMAIL_SUBJECT = "email.subject";

    // ========= SMS =========
    public static final String SMS_PROVIDER = "sms.provider";
    public static final String SMS_API_KEY = "sms.apiKey";
    public static final String SMS_PHONE = "sms.phone";

    // ========= SLACK =========
    public static final String SLACK_PROVIDER = "slack.provider";
    public static final String SLACK_TOKEN = "slack.token";
    public static final String SLACK_CHANNEL = "slack.channel";

    // ========= TEAMS =========
    public static final String TEAMS_PROVIDER = "teams.provider";
    public static final String TEAMS_WEBHOOK = "teams.webhook";
    public static final String TEAMS_CONVERSATION = "teams.conversation";
}
