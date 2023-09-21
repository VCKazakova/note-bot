package ru.vckazakova.notebot.repositoryDecorator.noteRepository.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.vckazakova.notebot.repositoryDecorator.noteRepository.repository.entity.NoteEntity;

public interface NoteRepository extends MongoRepository<NoteEntity, String> {

}
