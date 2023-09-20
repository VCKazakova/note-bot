package ru.vckazakova.notebot.repositoryDecorator.tagRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.vckazakova.notebot.repositoryDecorator.tagRepository.repository.TagRepository;
import ru.vckazakova.notebot.repositoryDecorator.tagRepository.repository.TagEntity;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TagRepositoryDecoratorImpl implements TagRepositoryDecorator {

    private final MongoTemplate mongoTemplate;
    private final TagRepository tagRepository;

    @Override
    @Transactional
    public void saveTag(TagEntity tagEntity) {
        tagRepository.save(tagEntity);
    }

    @Override
    @Transactional
    public void updateTagName(String oldTagName, String newTagName) {
        Query query = new Query(Criteria.where("name").is(oldTagName));
        Update update = new Update().set("name", newTagName);
        mongoTemplate.updateFirst(query, update, TagEntity.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TagEntity> findAll() {
        return tagRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteTagByName(String tagName) {
        tagRepository.deleteTagByName(tagName);
    }
}
