package com.novacomp.notification.infrastructure.utils;

public class Constants {
    public static final String ID = "id";
    public static final String AT_SIGN = "@";
    public static final String NOTIFICATION_CHANNEL_MSG = "Notification channel not supported: ";
    public static final String NOT_BE_NULL = "Notification must not be null";
    public static final String RECIPIENT_IS_REQUIRED = "Recipient is required";
    public static final String AT_LEAST_ONE_CHANNEL = "At least one channel is required";
    public static final String CREATION_DATE_IS_REQUIRED = "createdAt is required";
    public static final String CONTENT_CANT_NOT_BE_NULL = "Content cant not be null";
    public static final String INVALID_PHONE_NUMBER = "Invalid phone number: ";
    public static final String INVALID_CHANNEL = "Invalid groups in metadata: ";
    public static final String LOG_FAILED_TO_SERIALIZE =  "Failed to serialize Notification to JSON";
    public static final String LOG_CHANNEL_FAILED =  "Channel {} failed";
    public static final String INVALID_EMAIL =  "Invalid email address: ";
    public static final String INVALID_ID =  "Invalid id: ";
    public static final String LOG_FAILED_TO_SEND =  "Failed publishing notification event to Kafka";
}
