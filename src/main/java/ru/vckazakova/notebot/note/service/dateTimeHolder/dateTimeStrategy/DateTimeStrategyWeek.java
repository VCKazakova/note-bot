package ru.vckazakova.notebot.note.service.dateTimeHolder.dateTimeStrategy;

import org.springframework.stereotype.Component;
import ru.vckazakova.notebot.utils.DateTimeUtils;

@Component(value = "WEEK")
public class DateTimeStrategyWeek implements DateTimeStrategy {

    @Override
    public Dates getDates() {
        return Dates.builder()
                .beginDateTime(DateTimeUtils.getStartOfCurrentWeek())
                .endDateTime(DateTimeUtils.getEndOfCurrentWeek())
                .build();
    }
}
