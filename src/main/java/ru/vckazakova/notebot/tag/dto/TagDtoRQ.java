package ru.vckazakova.notebot.tag.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class TagDtoRQ {

    @NotBlank(message = "Необходимо указать имя тэга")
    String name;

}
