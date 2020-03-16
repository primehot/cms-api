package com.binance.cms.api.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ofPattern;

public class DateUtil {

    public static final DateTimeFormatter UTC_DATE_TIME_FORMATTER = ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZone(ZoneId.of("UTC"));

    public static LocalDateTime formatDate(String date) {
        return LocalDateTime.parse(date, UTC_DATE_TIME_FORMATTER);
    }

    public static String formatDate(LocalDateTime date) {
        return date.format(UTC_DATE_TIME_FORMATTER);
    }

}
