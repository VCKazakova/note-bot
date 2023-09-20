package ru.vckazakova.notebot.repositoryDecorator.tagRepository.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.vckazakova.notebot.repositoryDecorator.IntegrationBased;
import ru.vckazakova.notebot.repositoryDecorator.tagRepository.repository.TagRepository;
import ru.vckazakova.notebot.repositoryDecorator.tagRepository.repository.TagEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("TagRepository should:")
class TagRepositoryTest extends IntegrationBased {

    @Autowired
    private TagRepository tagRepository;

    private final TagEntity tagEntity = new TagEntity("1", "#здоровье");

    @Test
    @DisplayName("создавать тэг")
    public void createTagTest() {
        tagRepository.save(tagEntity);
        Optional<TagEntity> byId = tagRepository.findById("1");
        assertTrue(byId.isPresent());
    }

    @Test
    @DisplayName("найти все тэги")
    public void findAllTagsTest() {
        tagRepository.save(tagEntity);
        List<TagEntity> all = tagRepository.findAll();
        assertFalse(all.isEmpty());
    }

    @Test
    @DisplayName("удалить тэг по имени")
    public void deleteTagByNameTest() {
        tagRepository.save(tagEntity);
        tagRepository.deleteTagByName("#здоровье");
        Optional<TagEntity> byId = tagRepository.findById("1");
        assertTrue(byId.isEmpty());
    }

}