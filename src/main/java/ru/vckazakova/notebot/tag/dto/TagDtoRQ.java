package ru.vckazakova.notebot.tag.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class TagDtoRQ {

    @Null
    String id;

    @NotBlank(message = "Необходимо указать имя тэга")
    String name;

}
