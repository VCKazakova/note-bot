package ru.vckazakova.notebot.note.service.dateTimeHolder.dateTimeStrategy;

import org.springframework.stereotype.Component;
import ru.vckazakova.notebot.utils.DateTimeUtils;

@Component(value = "MONTH")
public class DateTimeStrategyMonth implements DateTimeStrategy {

    @Override
    public Dates getDates() {
        return Dates.builder()
                .beginDateTime(DateTimeUtils.getStartOfCurrentMonth())
                .endDateTime(DateTimeUtils.getEndOfCurrentMonth())
                .build();
    }
}
