package ru.vckazakova.notebot.repositoryDecorator.note.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document("notes")
public class NoteEntity {

    @Id
    @Indexed(unique = true)
    private String id;

    private String tag;

    private String text;

    private LocalDateTime dateTime;

}
