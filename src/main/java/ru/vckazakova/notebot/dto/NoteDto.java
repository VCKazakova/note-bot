package ru.vckazakova.notebot.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NoteDto {

    private String tag;

    private String text;

    private String dateTime;
}
