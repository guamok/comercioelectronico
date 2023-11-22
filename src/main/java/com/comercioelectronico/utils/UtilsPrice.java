package com.comercioelectronico.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UtilsPrice {

    public static LocalDateTime parserLocalDate(String appDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm:ss");
        LocalDateTime localDateTimePrice = LocalDateTime.parse(appDate, formatter);
        return localDateTimePrice;
    }
}
