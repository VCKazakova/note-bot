package ru.vckazakova.notebot.note.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Value
@Builder
@Jacksonized
public class NoteDtoRQ {

    @Null
    String id;

    @Pattern(regexp = "^#.*$", message = "Тэг должен иметь формат #sometag")
    @NotBlank(message = "Тэг не может быть пустым")
    String tag;

    @NotBlank(message = "Текст заметки должен быть заполнен")
    String text;

    @Null
    LocalDateTime dateTime;
}
