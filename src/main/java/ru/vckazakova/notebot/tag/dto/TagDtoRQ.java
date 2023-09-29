package ru.vckazakova.notebot.tag.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class TagDtoRQ {

    @Null
    String id;

    @Pattern(regexp = "^#.*$", message = "Имя тэга должно иметь формат #sometag")
    @NotBlank(message = "Необходимо указать имя тэга")
    String name;

}
