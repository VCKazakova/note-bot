package ru.vckazakova.notebot.note.service.dateTimeHolder.dateTimeStrategy;

import org.springframework.stereotype.Component;
import ru.vckazakova.notebot.utils.DateTimeUtils;

@Component(value = "TODAY")
public class DateTimeStrategyToday implements DateTimeStrategy {

    @Override
    public Dates getDates() {
        return Dates.builder()
                .beginDateTime(DateTimeUtils.getStartOfCurrentDay())
                .endDateTime(DateTimeUtils.getEndOfCurrentDay())
                .build();
    }
}
