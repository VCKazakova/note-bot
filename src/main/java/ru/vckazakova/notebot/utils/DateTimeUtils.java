package ru.vckazakova.notebot.utils;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateTimeUtils {

    public static LocalDateTime getStartOfCurrentDay() {
        try {
            return LocalDateTime.now().with(LocalTime.MIN);
        } catch (DateTimeException | ArithmeticException exception) {
            throw new DateTimeException("Ошибка преобразования времени " + exception.getCause());
        }
    }

    public static LocalDateTime getEndOfCurrentDay() {
        try {
            return LocalDateTime.now().with(LocalTime.MAX);
        } catch (DateTimeException | ArithmeticException exception) {
            throw new DateTimeException("Ошибка преобразования времени " + exception.getCause());
        }
    }

    public static LocalDateTime getStartOfCurrentWeek() {
        try {
            return LocalDateTime.now().with(DayOfWeek.MONDAY).with(LocalTime.MIN);
        } catch (DateTimeException | ArithmeticException exception) {
            throw new DateTimeException("Ошибка преобразования времени " + exception.getCause());
        }
    }

    public static LocalDateTime getEndOfCurrentWeek() {
        try {
            return LocalDateTime.now().with(DayOfWeek.SUNDAY).with(LocalTime.MAX);
        } catch (DateTimeException | ArithmeticException exception) {
            throw new DateTimeException("Ошибка преобразования времени " + exception.getCause());
        }
    }

    public static LocalDateTime getStartOfCurrentMonth() {
        try {
            return LocalDateTime.now().withDayOfMonth(1).with(LocalTime.MIN);
        } catch (DateTimeException | ArithmeticException exception) {
            throw new DateTimeException("Ошибка преобразования времени " + exception.getCause());
        }
    }

    public static LocalDateTime getEndOfCurrentMonth() {
        try {
            return LocalDateTime.now().withDayOfMonth(LocalDateTime.now().getMonth().maxLength()).with(LocalTime.MAX);

        } catch (DateTimeException | ArithmeticException exception) {
            throw new DateTimeException("Ошибка преобразования времени " + exception.getCause());
        }
    }

}
