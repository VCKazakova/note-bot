package ru.vckazakova.notebot.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.vckazakova.notebot.IntegrationBased;
import ru.vckazakova.notebot.model.Tag;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@DisplayName("TagRepositoryDecorator should:")
class TagRepositoryDecoratorTest extends IntegrationBased {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private TagRepositoryDecorator tagRepositoryDecorator;

    @Test
    @DisplayName("обновить тег по имени")
    public void updateTagByNameTest() {
        Tag tag = new Tag("1", "#здоровье");
        tagRepository.save(tag);
        tagRepositoryDecorator.updateTagName("#здоровье", "#здоровьеиспорт");
        Optional<Tag> byId = tagRepository.findById("1");
        assertTrue(byId.isPresent());
        assertEquals("#здоровьеиспорт", byId.get().getName());
    }

//    @Test
    @DisplayName("обновить тег по имени")
    public void updateTagByName2Test() {
        Tag tag = new Tag("2", "#фильмы");
        tagRepository.save(tag);
        tagRepositoryDecorator.updateTagName2("#фильмы", "#кино");
        Optional<Tag> byId = tagRepository.findById("2");
        assertTrue(byId.isPresent());
        assertEquals("#кино", byId.get().getName());
    }
}