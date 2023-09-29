package ru.vckazakova.notebot.repositoryDecorator.tag;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.vckazakova.notebot.repositoryDecorator.tag.repository.TagEntity;
import ru.vckazakova.notebot.repositoryDecorator.tag.repository.TagRepository;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TagRepositoryDecoratorImpl implements TagRepositoryDecorator {

    private final MongoTemplate mongoTemplate;
    private final TagRepository tagRepository;

    @Override
    @Transactional
    public TagEntity saveTag(TagEntity tagEntity) {
        return tagRepository.save(tagEntity);
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
    public List<TagEntity> findAll(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<TagEntity> all = tagRepository.findAll(paging);
        return all.getContent();
    }

    @Override
    @Transactional
    public void deleteTagByName(String tagName) {
        tagRepository.deleteTagByName(tagName);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TagEntity> findTagByName(String tagName) {
        return tagRepository.findTagEntityByName(tagName);
    }
}
