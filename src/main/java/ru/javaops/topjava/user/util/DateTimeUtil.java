package ru.javaops.topjava.user.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.LocalDateTime;

@UtilityClass
public class DateTimeUtil {

    // DB doesn't support LocalDate.MIN/MAX
    private static final LocalDateTime MIN_DATETIME = LocalDateTime.of(1, 1, 1, 0, 0);
    private static final LocalDateTime MAX_DATETIME = LocalDateTime.of(3000, 1, 1, 0, 0);
    private static final LocalDate MIN_DATE = LocalDate.of(1, 1, 1);
    private static final LocalDate MAX_DATE = LocalDate.of(3000, 1, 1);

    public static LocalDateTime atStartOfDayOrMin(LocalDate localDate) {
        return localDate != null ? localDate.atStartOfDay() : MIN_DATETIME;
    }

    public static LocalDateTime atStartOfNextDayOrMax(LocalDate localDate) {
        return localDate != null ? localDate.plusDays(1).atStartOfDay() : MAX_DATETIME;
    }

    public static LocalDate orMin(LocalDate localDate) {
        return localDate != null ? localDate : MIN_DATE;
    }

    public static LocalDate orMax(LocalDate localDate) {
        return localDate != null ? localDate : MAX_DATE;
    }
}