package com.binance.cms.api.mapper;

import org.modelmapper.Converter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.binance.cms.api.util.DateUtil.formatDate;

public abstract class MapperConverter {

    public static Converter<LocalDateTime, String> localDateTimeToString = ctx -> ctx.getSource() == null ? null : formatDate(ctx.getSource());
    public static Converter<String, LocalDateTime> stringToLocalDateTime = ctx -> ctx.getSource() == null ? null : formatDate(ctx.getSource());

    public static Converter<UUID, String> uuidToString = ctx -> ctx.getSource() == null ? null : ctx.getSource().toString();
    public static Converter<String, UUID> stringToUuid = ctx -> ctx.getSource() == null ? null : UUID.fromString(ctx.getSource());

    public static Converter<BigDecimal, String> bigDecimalToString = ctx -> ctx.getSource() == null ? null : ctx.getSource().toString();
    public static Converter<String, BigDecimal> stringToBigDecimal = ctx -> ctx.getSource() == null ? null : new BigDecimal(ctx.getSource());

}
