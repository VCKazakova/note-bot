package ru.vckazakova.notebot.utils.validation;

import java.time.LocalDateTime;

public interface DateTimeValidator {

    void validatePeriod(LocalDateTime fromDate, LocalDateTime toDate);

}
