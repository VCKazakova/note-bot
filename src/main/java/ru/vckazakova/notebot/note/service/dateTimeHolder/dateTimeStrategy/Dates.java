package ru.vckazakova.notebot.note.service.dateTimeHolder.dateTimeStrategy;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class Dates {

    LocalDateTime beginDateTime;

    LocalDateTime endDateTime;

}
