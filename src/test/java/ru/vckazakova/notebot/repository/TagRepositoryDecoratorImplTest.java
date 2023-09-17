package ru.vckazakova.notebot.repository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.vckazakova.notebot.IntegrationBased;
import ru.vckazakova.notebot.model.Tag;

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
        Tag tag = new Tag("1", "#здоровье");
        tagRepository.save(tag);
        Tag tag2 = new Tag("2", "#фильмы");
        tagRepository.save(tag2);
    }

    @AfterEach
    public void flush() {
        tagRepository.deleteAll();
    }

    @Test
    @DisplayName("сохранить тэг")
    public void saveTagTest() {
        Tag tag = new Tag("11", "#art");
        tagRepositoryDecorator.saveTag(tag);
        Optional<Tag> byId = tagRepository.findById("11");
        assertTrue(byId.isPresent());
        assertEquals("#art", byId.get().getName());
    }

    @Test
    @DisplayName("обновить тег по имени")
    public void updateTagByNameTest() {
        tagRepositoryDecorator.updateTagName("#здоровье", "#здоровьеиспорт");
        Optional<Tag> byId = tagRepository.findById("1");
        assertTrue(byId.isPresent());
        assertEquals("#здоровьеиспорт", byId.get().getName());
    }

//    @Test
    @DisplayName("обновить тег по имени")
    public void updateTagByName2Test() {
        tagRepositoryDecorator.updateTagName2("#фильмы", "#кино");
        Optional<Tag> byId = tagRepository.findById("2");
        assertTrue(byId.isPresent());
        assertEquals("#кино", byId.get().getName());
    }

    @Test
    @DisplayName("найти все тэги")
    public void findAllTest() {
        List<Tag> all = tagRepositoryDecorator.findAll();
        assertFalse(all.isEmpty());
        assertEquals(2, all.size());
    }

    @Test
    @DisplayName("удалить тэг по имени")
    public void deleteTagByNameTest() {
        tagRepositoryDecorator.deleteTagByName("#здоровье");
        Optional<Tag> byId = tagRepository.findById("1");
        assertTrue(byId.isEmpty());
    }

}