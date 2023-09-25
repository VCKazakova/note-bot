package ru.vckazakova.notebot.tag;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.vckazakova.notebot.tag.dto.TagDtoRQ;
import ru.vckazakova.notebot.tag.service.TagService;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("TagController should:")
@WebMvcTest(controllers = TagControllerImpl.class)
class TagControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TagService tagService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("не создавать тэг, если не передано его имя")
    public void notCreateTagTest() throws Exception {
        mockMvc.perform(post("/v1/tags")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(TagDtoRQ.builder().build())))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("создавать тэг, если имя передано корректно")
    public void createTagTest() throws Exception {
        mockMvc.perform(post("/v1/tags")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(TagDtoRQ.builder()
                                .name("#test")
                                .build())))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("получить все тэги")
    public void getAllTagsTest() throws Exception {
        mockMvc.perform(get("/v1/tags"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @DisplayName("обновить тэг по имени")
    public void updateTagByNameTest() throws Exception {
        String oldTagName = "#oldTagName";
        String newTagName = "#newTagName";
        String expectedResponse = "Тэг успешно обновлен, новое имя #newTagName";

        when(tagService.updateTagByName(oldTagName, newTagName)).thenReturn(expectedResponse);

        mockMvc.perform(patch("/v1/tags/{tagName}", oldTagName)
                        .param("newTagName", newTagName))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }

    @Test
    @DisplayName("выбрасывать исключение, если новое имя не прошло валидацию")
    public void notUpdateTagByNameTest() {
        String oldTagName = "oldTagName";
        String newTagName = "";

        assertThrows(ServletException.class, () -> mockMvc.perform(patch("/v1/tags/{tagName}", oldTagName)
                .param("newTagName", newTagName)));
    }

    @Test
    @DisplayName("удалить тэг по имени")
    public void deleteTagByNameTest() throws Exception {
        String tagName = "#deleting";
        String expectedResponse = "#deleting тэг успешно удален";

        when(tagService.deleteTagByName(tagName)).thenReturn(expectedResponse);

        mockMvc.perform(delete("/v1/tags/{tagName}", tagName))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }
}