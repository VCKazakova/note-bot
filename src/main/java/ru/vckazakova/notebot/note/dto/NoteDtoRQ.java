package ru.vckazakova.notebot.note.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Value
@Builder
@Jacksonized
public class NoteDtoRQ {

    @NotBlank(message = "Тэг должен иметь формат #sometag")
    String tag;

    @NotBlank(message = "Текст заметки должен быть заполнен")
    String text;

    LocalDateTime dateTime;
}
