package ru.vckazakova.notebot.repositoryDecorator.tag;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.vckazakova.notebot.repositoryDecorator.IntegrationBased;
import ru.vckazakova.notebot.repositoryDecorator.tag.repository.TagEntity;
import ru.vckazakova.notebot.repositoryDecorator.tag.repository.TagRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional(transactionManager = "transactionManager")
@DisplayName("TagRepositoryDecoratorImpl should:")
class TagRepositoryDecoratorImplTest extends IntegrationBased {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private TagRepositoryDecorator tagRepositoryDecorator;

    @Test
    @DisplayName("сохранить тэг")
    public void saveTagTest() {
        TagEntity tagEntity = new TagEntity("11", "#art");
        tagRepositoryDecorator.saveTag(tagEntity);
        Optional<TagEntity> byId = tagRepository.findById("11");
        assertTrue(byId.isPresent());
        assertEquals("#art", byId.get().getName());
    }

    @Test
    @DisplayName("обновить тег по имени")
    public void updateTagByNameTest() {
        TagEntity tagEntity = new TagEntity("1", "#здоровье");
        tagRepository.save(tagEntity);
        tagRepositoryDecorator.updateTagName("#здоровье", "#здоровьеиспорт");
        Optional<TagEntity> byId = tagRepository.findById("1");
        assertTrue(byId.isPresent());
        assertEquals("#здоровьеиспорт", byId.get().getName());
    }

    @Test
    @DisplayName("найти все тэги")
    public void findAllTest() {
        TagEntity tagEntity = new TagEntity("1", "#здоровье");
        tagRepository.save(tagEntity);
        TagEntity tagEntity2 = new TagEntity("2", "#фильмы");
        tagRepository.save(tagEntity2);
        List<TagEntity> all = tagRepositoryDecorator.findAll(0, 3);
        assertFalse(all.isEmpty());
        assertEquals(2, all.size());
    }

    @Test
    @DisplayName("найти все тэги на 2-ой странице")
    public void findAllOnSecondPageTest() {
        TagEntity tagEntity = new TagEntity("1", "#здоровье");
        tagRepository.save(tagEntity);
        TagEntity tagEntity2 = new TagEntity("2", "#фильмы");
        tagRepository.save(tagEntity2);
        List<TagEntity> all = tagRepositoryDecorator.findAll(1, 3);
        assertTrue(all.isEmpty());
        assertEquals(0, all.size());
    }

    @Test
    @DisplayName("удалить тэг по имени")
    public void deleteTagByNameTest() {
        TagEntity tagEntity = new TagEntity("1", "#здоровье");
        tagRepository.save(tagEntity);
        tagRepositoryDecorator.deleteTagByName("#здоровье");
        Optional<TagEntity> byId = tagRepository.findById("1");
        assertTrue(byId.isEmpty());
    }

}