package ru.vckazakova.notebot.repositoryDecorator.tagRepository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.vckazakova.notebot.repositoryDecorator.IntegrationBased;
import ru.vckazakova.notebot.repositoryDecorator.tagRepository.repository.TagRepository;
import ru.vckazakova.notebot.repositoryDecorator.tagRepository.repository.TagEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("TagRepositoryDecoratorImpl should:")
class TagRepositoryDecoratorImplTest extends IntegrationBased {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private TagRepositoryDecorator tagRepositoryDecorator;

    @BeforeEach
    public void init() {
        TagEntity tagEntity = new TagEntity("1", "#здоровье");
        tagRepository.save(tagEntity);
        TagEntity tagEntity2 = new TagEntity("2", "#фильмы");
        tagRepository.save(tagEntity2);
    }

    @AfterEach
    public void flush() {
        tagRepository.deleteAll();
    }

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
        tagRepositoryDecorator.updateTagName("#здоровье", "#здоровьеиспорт");
        Optional<TagEntity> byId = tagRepository.findById("1");
        assertTrue(byId.isPresent());
        assertEquals("#здоровьеиспорт", byId.get().getName());
    }

    @Test
    @DisplayName("найти все тэги")
    public void findAllTest() {
        List<TagEntity> all = tagRepositoryDecorator.findAll();
        assertFalse(all.isEmpty());
        assertEquals(2, all.size());
    }

    @Test
    @DisplayName("удалить тэг по имени")
    public void deleteTagByNameTest() {
        tagRepositoryDecorator.deleteTagByName("#здоровье");
        Optional<TagEntity> byId = tagRepository.findById("1");
        assertTrue(byId.isEmpty());
    }

}