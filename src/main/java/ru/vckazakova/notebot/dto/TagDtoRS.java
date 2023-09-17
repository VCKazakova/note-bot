package ru.vckazakova.notebot.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class TagDtoRS {

    String name;

}
