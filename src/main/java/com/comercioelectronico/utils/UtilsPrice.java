package com.comercioelectronico.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UtilsPrice {

    static final String PATTERN_DATE = "yyyy-MM-dd:HH:mm:ss";

    public static LocalDateTime parserLocalDate(String appDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_DATE);
        return LocalDateTime.parse(appDate, formatter);
    }
}
