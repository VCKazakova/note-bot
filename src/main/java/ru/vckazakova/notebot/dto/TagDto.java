package ru.vckazakova.notebot.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "id")
public class TagDto {

    private String id;

    private String name;

}
