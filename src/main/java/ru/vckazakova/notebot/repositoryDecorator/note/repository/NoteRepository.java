package ru.vckazakova.notebot.repositoryDecorator.note.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.vckazakova.notebot.repositoryDecorator.note.repository.entity.NoteEntity;

public interface NoteRepository extends MongoRepository<NoteEntity, String> {

}
