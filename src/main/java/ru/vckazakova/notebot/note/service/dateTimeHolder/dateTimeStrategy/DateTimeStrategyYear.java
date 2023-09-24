package ru.vckazakova.notebot.note.service.dateTimeHolder.dateTimeStrategy;

import org.springframework.stereotype.Component;
import ru.vckazakova.notebot.utils.DateTimeUtils;

@Component(value = "YEAR")
public class DateTimeStrategyYear implements DateTimeStrategy {

    @Override
    public Dates getDates() {
        return Dates.builder()
                .beginDateTime(DateTimeUtils.getStartOfCurrentYear())
                .endDateTime(DateTimeUtils.getEndOfCurrentYear())
                .build();
    }
}
