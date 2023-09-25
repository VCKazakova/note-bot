package ru.vckazakova.notebot.repositoryDecorator.note;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.vckazakova.notebot.repositoryDecorator.note.repository.NoteRepository;
import ru.vckazakova.notebot.repositoryDecorator.note.repository.entity.NoteEntity;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class NoteRepositoryDecoratorImpl implements NoteRepositoryDecorator {

    private final NoteRepository noteRepository;
    private final MongoTemplate mongoTemplate;
    private final QueryBuilder queryBuilder;

    @Override
    @Transactional
    public NoteEntity saveNote(NoteEntity noteEntity) {
        return noteRepository.save(noteEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NoteEntity> findAllByParameters(Map<String, Object> parameters, int page, int size) {
        Query query = queryBuilder.createQuery(parameters, page, size);
        return mongoTemplate.find(query, NoteEntity.class);
    }
}
