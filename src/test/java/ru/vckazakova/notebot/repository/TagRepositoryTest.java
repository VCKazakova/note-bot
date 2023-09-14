package ru.vckazakova.notebot.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.vckazakova.notebot.IntegrationBased;
import ru.vckazakova.notebot.model.Tag;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("TagRepository should:")
class TagRepositoryTest extends IntegrationBased {

    @Autowired
    private TagRepository tagRepository;

    private final Tag tag = new Tag("1", "#здоровье");

    @Test
    @DisplayName("создавать тэг")
    public void createTagTest() {
        tagRepository.save(tag);
        Optional<Tag> byId = tagRepository.findById("1");
        assertTrue(byId.isPresent());
    }

    @Test
    @DisplayName("найти все тэги")
    public void findAllTagsTest() {
        tagRepository.save(tag);
        List<Tag> all = tagRepository.findAll();
        assertFalse(all.isEmpty());
    }

    @Test
    @DisplayName("удалить тэг по имени")
    public void deleteTagByNameTest() {
        tagRepository.save(tag);
        tagRepository.deleteTagByName("#здоровье");
        Optional<Tag> byId = tagRepository.findById("1");
        assertTrue(byId.isEmpty());
    }

}