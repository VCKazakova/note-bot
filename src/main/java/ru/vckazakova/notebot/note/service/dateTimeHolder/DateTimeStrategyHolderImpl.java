package ru.vckazakova.notebot.note.service.dateTimeHolder;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.vckazakova.notebot.note.service.dateTimeHolder.dateTimeStrategy.DateTimeStrategy;
import ru.vckazakova.notebot.note.service.dateTimeHolder.dateTimeStrategy.Dates;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class DateTimeStrategyHolderImpl implements DateTimeStrategyHolder {

    private final Map<String, DateTimeStrategy> strategyHolder;

    @Override
    public Dates getDateTimeByStrategy(String period) {
        DateTimeStrategy dateTimeStrategy = strategyHolder.get(period);
        return dateTimeStrategy.getDates();
    }
}
