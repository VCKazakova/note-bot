package ru.vckazakova.notebot.note;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.vckazakova.notebot.note.dto.NoteDtoRQ;
import ru.vckazakova.notebot.note.service.NoteService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("NoteController should:")
@WebMvcTest(controllers = NoteControllerImpl.class)
class NoteControllerImplTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private NoteService noteService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("не создавать заметку, если не передан тэг")
    public void notCreateNoteIfNoTagTest() throws Exception {
        mockMvc.perform(post("/v1/notes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(NoteDtoRQ.builder().build())))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("не создавать заметку, если не передан текст")
    public void notCreateNoteIfNoTextTest() throws Exception {
        mockMvc.perform(post("/v1/notes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(NoteDtoRQ.builder()
                                .tag("#test")
                                .build())))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("создать заметку, если переданы корректные данные")
    public void createNoteTest() throws Exception {
        mockMvc.perform(post("/v1/notes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(NoteDtoRQ.builder()
                                .tag("#test")
                                .text("create new note")
                                .build())))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("получить все заметки")
    public void getAllNotesTest() throws Exception {
        mockMvc.perform(get("/v1/notes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @DisplayName("получить все заметки по тэгу")
    public void getAllNotesByTagTest() throws Exception {
        mockMvc.perform(get("/v1/notes")
                        .param("tag", "#tag"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @DisplayName("получить все заметки по дате от")
    public void getAllNotesByFromDateTest() throws Exception {
        mockMvc.perform(get("/v1/notes")
                        .param("fromDate", "2023-01-01T00:00:00"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @DisplayName("получить все заметки по дате по")
    public void getAllNotesByToDateTest() throws Exception {
        mockMvc.perform(get("/v1/notes")
                        .param("toDate", "2023-01-01T00:00:00"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @DisplayName("получить все заметки по дате от и тэгу")
    public void getAllNotesByFromDateAndTagTest() throws Exception {
        mockMvc.perform(get("/v1/notes")
                        .param("tag", "#tag")
                        .param("fromDate", "2023-01-01T00:00:00"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @DisplayName("получить все заметки по дате по и тэгу")
    public void getAllNotesByToDateAndTagTest() throws Exception {
        mockMvc.perform(get("/v1/notes")
                        .param("tag", "#tag")
                        .param("toDate", "2023-01-01T00:00:00"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @DisplayName("получить все заметки по периоду")
    public void getAllNotesOnlyByPeriodTest() throws Exception {
        mockMvc.perform(get("/v1/notes/byPeriod")
                        .param("period", "TODAY"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @DisplayName("получить все заметки по периоду и тэгу")
    public void getAllNotesByPeriodAndTagTest() throws Exception {
        mockMvc.perform(get("/v1/notes/byPeriod")
                        .param("tag", "#tag")
                        .param("period", "TODAY"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @DisplayName("получить все заметки")
    public void getAllNotesByPeriodTest() throws Exception {
        mockMvc.perform(get("/v1/notes/byPeriod"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

}