package ru.vckazakova.notebot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.vckazakova.notebot.model.Note;

public interface NoteRepository extends MongoRepository<Note, String> {
}
