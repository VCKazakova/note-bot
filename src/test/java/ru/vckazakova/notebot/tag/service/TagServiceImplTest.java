package ru.vckazakova.notebot.tag.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.vckazakova.notebot.tag.dto.TagDtoRQ;
import ru.vckazakova.notebot.tag.dto.TagDtoRS;
import ru.vckazakova.notebot.repositoryDecorator.tagRepository.repository.TagEntity;
import ru.vckazakova.notebot.repositoryDecorator.tagRepository.TagRepositoryDecorator;

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
    private TagMapper tagMapper;

    @InjectMocks
    private TagServiceImpl tagService;

    @Test
    @DisplayName("создавать тэг")
    public void createTagTest() {
        TagDtoRQ tagDtoRQ = TagDtoRQ.builder()
                .name("#popopo")
                .build();
        when(tagMapper.mapTag(tagDtoRQ)).thenReturn(new TagEntity(null, "#popopo"));

        String createTag = tagService.createTag(tagDtoRQ);
        assertEquals("#popopo тэг успешно создан", createTag);
    }

    @Test
    @DisplayName("находить все тэги")
    public void findAllTagsTest() {
        List<TagEntity> tagEntities = List.of(new TagEntity("1", "фильмы"), new TagEntity("2", "музыка"));

        when(tagRepositoryDecorator.findAll()).thenReturn(tagEntities);
        when(tagMapper.mapTagDto(any())).thenReturn(TagDtoRS.builder().build());

        List<TagDtoRS> allTags = tagService.findAllTags();

        verify(tagMapper, times(2)).mapTagDto(any());
        verify(tagRepositoryDecorator, times(1)).findAll();
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
        doNothing().when(tagRepositoryDecorator).deleteTagByName(tagName);
        String result = tagService.deleteTagByName(tagName);
        verify(tagRepositoryDecorator, times(1)).deleteTagByName(tagName);
        assertEquals(tagName + " тэг успешно удален", result);
    }

}