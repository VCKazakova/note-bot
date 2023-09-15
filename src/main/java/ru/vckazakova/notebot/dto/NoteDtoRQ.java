package ru.vckazakova.notebot.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Value
@Builder
@Jacksonized
public class NoteDtoRQ {

    String tag;

    String text;

    LocalDateTime dateTime;
}
