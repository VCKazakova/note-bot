package ru.vckazakova.notebot.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.vckazakova.notebot.tag.dto.TagDtoRQ;
import ru.vckazakova.notebot.tag.dto.TagDtoRS;
import ru.vckazakova.notebot.tag.service.TagService;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("TagHelperImpl should:")
class TagHelperImplTest {

    @Mock
    private TagService tagService;
    @InjectMocks
    private TagHelperImpl tagHelper;

    @Test
    @DisplayName("не выполнять сохранение тэга, если он найден в базе")
    public void notSaveTagTest() {
        String tag = "#test";

        when(tagService.findTagByName(tag)).thenReturn(Optional.of(TagDtoRS.builder()
                .name(tag)
                .build()));

        tagHelper.saveTag(tag);

        verify(tagService, times(1)).findTagByName(tag);
        verify(tagService, times(0)).createTag(any());

    }

    @Test
    @DisplayName("выполнять сохранение тэга, если он найден в базе")
    public void saveTagTest() {
        String tag = "#test";

        when(tagService.findTagByName(tag)).thenReturn(Optional.empty());

        tagHelper.saveTag(tag);

        verify(tagService, times(1)).findTagByName(tag);
        verify(tagService, times(1)).createTag(TagDtoRQ.builder()
                .name(tag)
                .build());

    }

}