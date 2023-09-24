package ru.vckazakova.notebot.note.service.dateTimeHolder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.vckazakova.notebot.note.service.dateTimeHolder.dateTimeStrategy.Dates;

import static java.util.Objects.nonNull;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DateTimeStrategyHolderImpl should:")
@SpringBootTest
class DateTimeStrategyHolderImplTest {

    @Autowired
    private DateTimeStrategyHolder dateTimeStrategyHolder;

    @Test
    @DisplayName("возвращать дату по стратегии сегодня")
    public void getDateTimeByTodayStrategyTest() {
        Dates dateTimeByStrategy = dateTimeStrategyHolder.getDateTimeByStrategy("TODAY");
        assertTrue(nonNull(dateTimeByStrategy.getBeginDateTime()));
        assertTrue(nonNull(dateTimeByStrategy.getEndDateTime()));
    }

    @Test
    @DisplayName("возвращать дату по стратегии недели")
    public void getDateTimeByWeekStrategyTest() {
        Dates dateTimeByStrategy = dateTimeStrategyHolder.getDateTimeByStrategy("WEEK");
        assertTrue(nonNull(dateTimeByStrategy.getBeginDateTime()));
        assertTrue(nonNull(dateTimeByStrategy.getEndDateTime()));
    }

    @Test
    @DisplayName("возвращать дату по стратегии месяца")
    public void getDateTimeByMonthStrategyTest() {
        Dates dateTimeByStrategy = dateTimeStrategyHolder.getDateTimeByStrategy("MONTH");
        assertTrue(nonNull(dateTimeByStrategy.getBeginDateTime()));
        assertTrue(nonNull(dateTimeByStrategy.getEndDateTime()));
    }

    @Test
    @DisplayName("возвращать дату по стратегии года")
    public void getDateTimeByYearStrategyTest() {
        Dates dateTimeByStrategy = dateTimeStrategyHolder.getDateTimeByStrategy("YEAR");
        assertTrue(nonNull(dateTimeByStrategy.getBeginDateTime()));
        assertTrue(nonNull(dateTimeByStrategy.getEndDateTime()));
    }

}