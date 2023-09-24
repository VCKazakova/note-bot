package ru.vckazakova.notebot.note.service.dateTimeHolder;

import ru.vckazakova.notebot.note.service.dateTimeHolder.dateTimeStrategy.Dates;

public interface DateTimeStrategyHolder {

    Dates getDateTimeByStrategy(String period);

}
