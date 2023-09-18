package ru.vckazakova.notebot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.vckazakova.notebot.dto.NoteDtoRQ;
import ru.vckazakova.notebot.service.NoteService;

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
        mockMvc.perform(post("/note")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(NoteDtoRQ.builder().build())))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("не создавать заметку, если не передан текст")
    public void notCreateNoteIfNoTextTest() throws Exception {
        mockMvc.perform(post("/note")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(NoteDtoRQ.builder()
                                .tag("#test")
                                .build())))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("создать заметку, если переданы корректные данные")
    public void createNoteTest() throws Exception {
        mockMvc.perform(post("/note")
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
        mockMvc.perform(get("/note"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @DisplayName("получить все заметки за сегодняшний день")
    public void getAllTodayNotesTest() throws Exception {
        mockMvc.perform(get("/note/today"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @DisplayName("получить все заметки за текущую неделю")
    public void getAllCurrentWeekNotesTest() throws Exception {
        mockMvc.perform(get("/note/currentWeek"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @DisplayName("получить все заметки за текущий месяц")
    public void getAllCurrentMonthNotesTest() throws Exception {
        mockMvc.perform(get("/note/currentMonth"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @DisplayName("получить все заметки за указанный год")
    public void findYearNotesTest() throws Exception {

        mockMvc.perform(get("/note/year/{year}", 2022))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("получить все заметки за указанный период времени")
    public void findNotesByPeriodTest() throws Exception {


        mockMvc.perform(get("/note/byPeriod")
                        .param("fromDate", String.valueOf(fromDate))
                        .param("toDate", String.valueOf(toDate)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("получить все заметки по указанному тэгу")
    public void findNotesByTagTest() throws Exception {

        mockMvc.perform(get("/note/{tag}", "#art"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("получить все сегодняшние заметки по указанному тэгу")
    public void findTodayNotesByTagTest() throws Exception {

        mockMvc.perform(get("/note/today/{tag}", "#films"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("получить все заметки за текущую неделю по указанному тэгу")
    public void findCurrentWeekNotesByTagTest() throws Exception {

        mockMvc.perform(get("/note/currentWeek/{tag}", "#films"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("получить все заметки за текущий месяц по указанному тэгу")
    public void findCurrentMonthNotesByTag() throws Exception {

        mockMvc.perform(get("/note/currentMonth/{tag}", "#films"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("получить все заметки по указанному году и тэгу")
    public void findYearNotesByTagTest() throws Exception {

        mockMvc.perform(get("/note/{year}/{tag}", 2023, "#films"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("получить все заметки за период по указанному тэгу")
    public void findNotesByPeriodAndByTagTest() throws Exception {

        mockMvc.perform(get("/note/byPeriod/{tag}", "#films")
                .param("fromDate", String.valueOf(fromDate))
                .param("toDate", String.valueOf(toDate)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

}