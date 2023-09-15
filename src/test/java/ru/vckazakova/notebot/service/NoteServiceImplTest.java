package ru.vckazakova.notebot.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.vckazakova.notebot.MyFaker;
import ru.vckazakova.notebot.dto.NoteDtoRQ;
import ru.vckazakova.notebot.dto.NoteDtoRS;
import ru.vckazakova.notebot.mapper.NoteMapper;
import ru.vckazakova.notebot.model.Note;
import ru.vckazakova.notebot.repository.NoteRepositoryDecorator;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("NoteServiceImpl should:")
@ExtendWith(MockitoExtension.class)
class NoteServiceImplTest {

    @Mock
    private NoteRepositoryDecorator noteRepositoryDecorator;
    @Mock
    private NoteMapper noteMapper;
    @InjectMocks
    private NoteServiceImpl noteService;

    private static MyFaker myFaker;
    private static List<Note> randomListNote;
    private static List<NoteDtoRS> randomListNoteDtoRS;

    @BeforeAll
    public static void init() {
        myFaker = new MyFaker();
        randomListNote = myFaker.getRandomListNote(5);
        randomListNoteDtoRS = myFaker.getRandomListNoteDtoRS(5);
    }

    @Test
    @DisplayName("создавать заметку")
    public void createNoteTest() {
        NoteDtoRQ noteDtoRQ = NoteDtoRQ.builder()
                .tag("#art")
                .text("Culture is everything that man create")
                .dateTime(LocalDateTime.now())
                .build();

        doNothing().when(noteRepositoryDecorator).saveNote(any());
        when(noteMapper.mapNote(noteDtoRQ)).thenReturn(
                new Note("123", "#art", "Culture is everything that man create", LocalDateTime.now()));

        String note = noteService.createNote(noteDtoRQ);
        assertEquals("Culture is everything that man create заметка создана успешно", note);
        verify(noteRepositoryDecorator, times(1)).saveNote(any());
        verify(noteMapper, times(1)).mapNote(noteDtoRQ);
    }

    @Test
    @DisplayName("находить все заметки")
    public void findAllNotesTest() {
        when(noteRepositoryDecorator.findAllNotes()).thenReturn(randomListNote);
        when(noteMapper.mapListDto(randomListNote)).thenReturn(randomListNoteDtoRS);

        List<NoteDtoRS> allNotes = noteService.findAllNotes();

        assertEquals(5, allNotes.size());

        verify(noteRepositoryDecorator, times(1)).findAllNotes();
        verify(noteMapper, times(1)).mapListDto(randomListNote);

    }

    @Test
    @DisplayName("находить заметки за сегодняшний день")
    public void findTodayNotesTest() {
        when(noteRepositoryDecorator.findNotesForCurrentDay()).thenReturn(randomListNote);
        when(noteMapper.mapListDto(randomListNote)).thenReturn(randomListNoteDtoRS);

        List<NoteDtoRS> todayNotes = noteService.findTodayNotes();

        assertEquals(5, todayNotes.size());

        verify(noteRepositoryDecorator, times(1)).findNotesForCurrentDay();
        verify(noteMapper, times(1)).mapListDto(randomListNote);
    }

    @Test
    @DisplayName("находить заметки за текущую неделю")
    public void findCurrentWeekNotesTest() {
        when(noteRepositoryDecorator.findNotesForCurrentWeek()).thenReturn(randomListNote);
        when(noteMapper.mapListDto(randomListNote)).thenReturn(randomListNoteDtoRS);

        List<NoteDtoRS> todayNotes = noteService.findCurrentWeekNotes();

        assertEquals(5, todayNotes.size());

        verify(noteRepositoryDecorator, times(1)).findNotesForCurrentWeek();
        verify(noteMapper, times(1)).mapListDto(randomListNote);
    }

    @Test
    @DisplayName("находить заметки за текущий месяц")
    public void findCurrentMonthNotesTest() {
        when(noteRepositoryDecorator.findNotesForCurrentMonth()).thenReturn(randomListNote);
        when(noteMapper.mapListDto(randomListNote)).thenReturn(randomListNoteDtoRS);

        List<NoteDtoRS> todayNotes = noteService.findCurrentMonthNotes();

        assertEquals(5, todayNotes.size());

        verify(noteRepositoryDecorator, times(1)).findNotesForCurrentMonth();
        verify(noteMapper, times(1)).mapListDto(randomListNote);
    }

    @Test
    @DisplayName("находить заметки за год")
    public void findYearNotesTest() {
        int year = 2023;

        when(noteRepositoryDecorator.findAllNotesByYear(year)).thenReturn(randomListNote);
        when(noteMapper.mapListDto(randomListNote)).thenReturn(randomListNoteDtoRS);

        List<NoteDtoRS> todayNotes = noteService.findYearNotes(year);

        assertEquals(5, todayNotes.size());

        verify(noteRepositoryDecorator, times(1)).findAllNotesByYear(year);
        verify(noteMapper, times(1)).mapListDto(randomListNote);
    }

    @Test
    @DisplayName("находить заметки за период")
    public void findNotesByPeriodTest() {
        LocalDateTime startDateTime = LocalDateTime.of(2023, Month.APRIL, 1, 12, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2023, Month.SEPTEMBER, 30, 12, 0);


        when(noteRepositoryDecorator.findAllNotesByPeriod(startDateTime, endDateTime)).thenReturn(randomListNote);
        when(noteMapper.mapListDto(randomListNote)).thenReturn(randomListNoteDtoRS);

        List<NoteDtoRS> todayNotes = noteService.findNotesByPeriod(startDateTime, endDateTime);

        assertEquals(5, todayNotes.size());

        verify(noteRepositoryDecorator, times(1)).findAllNotesByPeriod(startDateTime, endDateTime);
        verify(noteMapper, times(1)).mapListDto(randomListNote);
    }

    @Test
    @DisplayName("находить заметки по тэгу")
    public void findNotesByTagTest() {
        String tag = "#tag";

        when(noteRepositoryDecorator.findAllNotesByTag(tag)).thenReturn(randomListNote);
        when(noteMapper.mapListDto(randomListNote)).thenReturn(randomListNoteDtoRS);

        List<NoteDtoRS> todayNotes = noteService.findNotesByTag(tag);

        assertEquals(5, todayNotes.size());

        verify(noteRepositoryDecorator, times(1)).findAllNotesByTag(tag);
        verify(noteMapper, times(1)).mapListDto(randomListNote);
    }

    @Test
    @DisplayName("находить заметки текущего дня по тэгу")
    public void findTodayNotesByTagTest() {
        String tag = "#tag";

        when(noteRepositoryDecorator.findNotesForCurrentDayByTag(tag)).thenReturn(randomListNote);
        when(noteMapper.mapListDto(randomListNote)).thenReturn(randomListNoteDtoRS);

        List<NoteDtoRS> todayNotes = noteService.findTodayNotesByTag(tag);

        assertEquals(5, todayNotes.size());

        verify(noteRepositoryDecorator, times(1)).findNotesForCurrentDayByTag(tag);
        verify(noteMapper, times(1)).mapListDto(randomListNote);
    }

    @Test
    @DisplayName("находить заметки текущей недели по тэгу")
    public void findCurrentWeekNotesByTagTest() {
        String tag = "#tag";

        when(noteRepositoryDecorator.findNotesForCurrentWeekByTag(tag)).thenReturn(randomListNote);
        when(noteMapper.mapListDto(randomListNote)).thenReturn(randomListNoteDtoRS);

        List<NoteDtoRS> todayNotes = noteService.findCurrentWeekNotesByTag(tag);

        assertEquals(5, todayNotes.size());

        verify(noteRepositoryDecorator, times(1)).findNotesForCurrentWeekByTag(tag);
        verify(noteMapper, times(1)).mapListDto(randomListNote);
    }

    @Test
    @DisplayName("находить заметки текущего месяца по тэгу")
    public void findCurrentMonthNotesByTagTest() {
        String tag = "#tag";

        when(noteRepositoryDecorator.findNotesForCurrentMonthByTag(tag)).thenReturn(randomListNote);
        when(noteMapper.mapListDto(randomListNote)).thenReturn(randomListNoteDtoRS);

        List<NoteDtoRS> todayNotes = noteService.findCurrentMonthNotesByTag(tag);

        assertEquals(5, todayNotes.size());

        verify(noteRepositoryDecorator, times(1)).findNotesForCurrentMonthByTag(tag);
        verify(noteMapper, times(1)).mapListDto(randomListNote);
    }

    @Test
    @DisplayName("находить заметки за год по тэгу")
    public void findYearNotesByTagTest() {
        int year = 2023;
        String tag = "#tag";

        when(noteRepositoryDecorator.findAllNotesByYearAndTag(year, tag)).thenReturn(randomListNote);
        when(noteMapper.mapListDto(randomListNote)).thenReturn(randomListNoteDtoRS);

        List<NoteDtoRS> todayNotes = noteService.findYearNotesByTag(year, tag);

        assertEquals(5, todayNotes.size());

        verify(noteRepositoryDecorator, times(1)).findAllNotesByYearAndTag(year, tag);
        verify(noteMapper, times(1)).mapListDto(randomListNote);
    }

    @Test
    @DisplayName("находить заметки за период по тэгу")
    public void findNotesByPeriodAndByTag() {
        LocalDateTime startDateTime = LocalDateTime.of(2023, Month.APRIL, 1, 12, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2023, Month.SEPTEMBER, 30, 12, 0);
        String tag = "#tag";

        when(noteRepositoryDecorator.findAllNotesByPeriodAndTag(startDateTime, endDateTime, tag)).thenReturn(randomListNote);
        when(noteMapper.mapListDto(randomListNote)).thenReturn(randomListNoteDtoRS);

        List<NoteDtoRS> todayNotes = noteService.findNotesByPeriodAndByTag(startDateTime, endDateTime, tag);

        assertEquals(5, todayNotes.size());

        verify(noteRepositoryDecorator, times(1)).findAllNotesByPeriodAndTag(startDateTime, endDateTime, tag);
        verify(noteMapper, times(1)).mapListDto(randomListNote);
    }

}