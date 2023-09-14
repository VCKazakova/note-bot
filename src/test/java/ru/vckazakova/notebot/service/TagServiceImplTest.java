package ru.vckazakova.notebot.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.vckazakova.notebot.dto.TagDto;
import ru.vckazakova.notebot.mapper.TagMapper;
import ru.vckazakova.notebot.model.Tag;
import ru.vckazakova.notebot.repository.TagRepository;
import ru.vckazakova.notebot.repository.TagRepositoryDecorator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("TagServiceImpl should:")
@ExtendWith(MockitoExtension.class)
class TagServiceImplTest {

    @Mock
    private TagRepositoryDecorator tagRepositoryDecorator;

    @Mock
    private TagRepository tagRepository;

    @Mock
    private TagMapper tagMapper;

    @InjectMocks
    private TagServiceImpl tagService;

    @Test
    @DisplayName("создавать тэг")
    public void createTagTest() {
        TagDto tagDto = new TagDto("1", "#popopo");
        when(tagMapper.mapTag(tagDto)).thenReturn(new Tag("1", "#popopo"));

        String createTag = tagService.createTag(tagDto);
        assertEquals("#popopo тэг успешно создан", createTag);
    }

    @Test
    @DisplayName("находить все тэги")
    public void findAllTagsTest() {
        List<Tag> tags = List.of(new Tag("1", "фильмы"), new Tag("2", "музыка"));

        when(tagRepository.findAll()).thenReturn(tags);
        when(tagMapper.mapTagDto(any())).thenReturn(new TagDto());

        List<TagDto> allTags = tagService.findAllTags();

        verify(tagMapper, times(2)).mapTagDto(any());
        verify(tagRepository, times(1)).findAll();
        assertEquals(2, allTags.size());
    }

    @Test
    @DisplayName("обновить тэг по имени")
    public void updateTagByNameTest() {
        String oldName = "#картины";
        String newName = "#искусство";
        doNothing().when(tagRepositoryDecorator).updateTagName(oldName, newName);
        String result = tagService.updateTagByName(oldName, newName);
        verify(tagRepositoryDecorator, times(1)).updateTagName(oldName, newName);
        assertEquals("Тэг успешно обновлен, новое имя " + newName, result);
    }

    @Test
    @DisplayName("удалить тэг по имени")
    public void deleteTagByNameTest() {
        String tagName = "#картины";
        doNothing().when(tagRepository).deleteTagByName(tagName);
        String result = tagService.deleteTagByName(tagName);
        verify(tagRepository, times(1)).deleteTagByName(tagName);
        assertEquals(tagName + " тэг успешно удален", result);
    }

}