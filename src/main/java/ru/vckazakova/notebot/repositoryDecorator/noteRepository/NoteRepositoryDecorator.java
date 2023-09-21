package ru.vckazakova.notebot.repositoryDecorator.noteRepository;

import ru.vckazakova.notebot.repositoryDecorator.noteRepository.repository.entity.NoteEntity;

import java.util.List;
import java.util.Map;

public interface NoteRepositoryDecorator {

    void saveNote(NoteEntity noteEntity);

    List<NoteEntity> findAllByParameters(Map<String, Object> parameters);
}
