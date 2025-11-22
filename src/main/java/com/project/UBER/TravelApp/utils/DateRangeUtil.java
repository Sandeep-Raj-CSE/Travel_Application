package com.project.UBER.TravelApp.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateRangeUtil {
    public static LocalDateTime startofDay(LocalDate d){
        return d == null ? LocalDateTime.of(1970,1,1,0,0) : d.atStartOfDay();
    }

    public static LocalDateTime endofDay(LocalDate d){
        return d == null ? LocalDateTime.now() : d.atTime(LocalTime.MAX);
    }
}
