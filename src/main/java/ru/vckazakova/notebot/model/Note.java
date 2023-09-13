package ru.vckazakova.notebot.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document("notes")
public class Note {

    @Id
    private String id;

    private String tag;

    private String text;

    private LocalDateTime dateTime;

}
