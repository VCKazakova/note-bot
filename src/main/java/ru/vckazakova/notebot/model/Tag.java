package ru.vckazakova.notebot.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document("tags")
public class Tag {

    @Id
    private String id;

    private String name;

}
