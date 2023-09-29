package ru.vckazakova.notebot.utils.validation;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static java.util.Objects.nonNull;

@Component
public class DateTimeValidatorImpl implements DateTimeValidator {

    @Override
    public void validatePeriod(LocalDateTime fromDate, LocalDateTime toDate) {
        if (nonNull(fromDate) && nonNull(toDate)) {
            if (fromDate.isAfter(toDate) || toDate.isBefore(fromDate)) {
                throw new RuntimeException(
                        "Неправильно передано значение LocalDateTime: fromDate должна быть меньше toDate");
            }
        }
    }
}
