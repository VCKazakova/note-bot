package ru.vckazakova.notebot.note.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class NoteDtoRS {

    String tag;

    String text;

    String dateTime;
}
