package ru.vckazakova.notebot.repositoryDecorator.note;

import ru.vckazakova.notebot.repositoryDecorator.note.repository.entity.NoteEntity;

import java.util.List;
import java.util.Map;

public interface NoteRepositoryDecorator {

    NoteEntity saveNote(NoteEntity noteEntity);

    List<NoteEntity> findAllByParameters(Map<String, Object> parameters, int page, int size);
}
