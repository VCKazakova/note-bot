package ru.vckazakova.notebot.repositoryDecorator.noteRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.vckazakova.notebot.repositoryDecorator.noteRepository.repository.NoteRepository;
import ru.vckazakova.notebot.repositoryDecorator.noteRepository.repository.entity.NoteEntity;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class NoteRepositoryDecoratorImpl implements NoteRepositoryDecorator {

    private final NoteRepository noteRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    @Transactional
    public void saveNote(NoteEntity noteEntity) {
        noteRepository.save(noteEntity);
    }

    public List<NoteEntity> findAllByParameters(Map<String, Object> parameters) {
        Criteria criteria = new Criteria();
        if (parameters.containsKey("fromDate") && parameters.containsKey("toDate")) {
            criteria.and("dateTime").gte(parameters.get("fromDate")).lte(parameters.get("toDate"));
        } else if (parameters.containsKey("toDate")) {
            criteria.and("dateTime").lte(parameters.get("toDate"));
        } else if (parameters.containsKey("fromDate")) {
            criteria.and("dateTime").gte(parameters.get("fromDate"));
        }
        if (parameters.containsKey("tag")) {
            criteria.and("tag").is(parameters.get("tag"));
        }
        Query query = new Query(criteria);
        return mongoTemplate.find(query, NoteEntity.class);
    }
}
