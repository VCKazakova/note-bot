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

import java.time.LocalDateTime;
import java.time.Month;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("NoteController should:")
@WebMvcTest(controllers = NoteController.class)
class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteService noteService;

    @Autowired
    private ObjectMapper objectMapper;

    private final LocalDateTime fromDate = LocalDateTime.of(2023, Month.APRIL, 1, 12, 0);
    private final LocalDateTime toDate = LocalDateTime.of(2023, Month.MAY, 1, 12, 0);

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

}