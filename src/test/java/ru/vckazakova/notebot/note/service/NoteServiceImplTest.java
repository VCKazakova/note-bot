package ru.vckazakova.notebot.note.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.vckazakova.notebot.common.TagHelper;
import ru.vckazakova.notebot.note.dto.NoteDtoRQ;
import ru.vckazakova.notebot.note.dto.NoteDtoRS;
import ru.vckazakova.notebot.note.dto.PeriodRQ;
import ru.vckazakova.notebot.note.service.dateTimeHolder.DateTimeStrategyHolder;
import ru.vckazakova.notebot.note.service.dateTimeHolder.dateTimeStrategy.Dates;
import ru.vckazakova.notebot.repositoryDecorator.note.NoteRepositoryDecorator;
import ru.vckazakova.notebot.repositoryDecorator.note.repository.entity.NoteEntity;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Mock
    private DateTimeStrategyHolder dateTimeStrategyHolder;
    @InjectMocks
    private NoteServiceImpl noteService;
    @Mock
    private TagHelper tagHelper;

    private static MyFaker myFaker;
    private static List<NoteDtoRS> randomListNoteDtoRS;

    @BeforeAll
    public static void init() {
        myFaker = new MyFaker();
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

        NoteEntity mapEntity =
                new NoteEntity("123", "#art", "Culture is everything that man create", LocalDateTime.now());

        when(noteRepositoryDecorator.saveNote(any())).thenReturn(mapEntity);
        when(noteMapper.mapNote(noteDtoRQ)).thenReturn(mapEntity);

        String note = noteService.createNote(noteDtoRQ);
        assertEquals(mapEntity + " заметка создана успешно", note);
        verify(noteRepositoryDecorator, times(1)).saveNote(any());
        verify(noteMapper, times(1)).mapNote(noteDtoRQ);
    }

    @Test
    @DisplayName("находить все заметки")
    public void findAllNotesByParametersAllNotesTest() {
        when(noteMapper.mapListDto(any())).thenReturn(randomListNoteDtoRS);

        List<NoteDtoRS> allNotes = noteService.findAllNotesByParameters(null, null, null, 0, 5);

        assertEquals(5, allNotes.size());

        verify(noteRepositoryDecorator, times(1)).findAllByParameters(new HashMap<>(), 0, 5);
        verify(noteMapper, times(1)).mapListDto(any());
    }

    @Test
    @DisplayName("находить все заметки по тэгу")
    public void findAllNotesByParametersAllNotesByTagTest() {
        when(noteMapper.mapListDto(any())).thenReturn(randomListNoteDtoRS);

        List<NoteDtoRS> allNotes = noteService.findAllNotesByParameters(null, null, "#tag", 0, 5);

        Map<String, Object> params = new HashMap<>();
        params.put("tag", "#tag");
        assertEquals(5, allNotes.size());

        verify(noteRepositoryDecorator, times(1)).findAllByParameters(params, 0, 5);
        verify(noteMapper, times(1)).mapListDto(any());
    }

    @Test
    @DisplayName("находить все заметки по дате после")
    public void findAllNotesByParametersAllNotesByFromDateTest() {
        when(noteMapper.mapListDto(any())).thenReturn(randomListNoteDtoRS);

        LocalDateTime dateTime = LocalDateTime.of(2022, Month.APRIL, 1, 4, 6);

        List<NoteDtoRS> allNotes = noteService.findAllNotesByParameters(dateTime, null, null, 0, 5);

        Map<String, Object> params = new HashMap<>();
        params.put("fromDate", dateTime);
        assertEquals(5, allNotes.size());

        verify(noteRepositoryDecorator, times(1)).findAllByParameters(params, 0, 5);
        verify(noteMapper, times(1)).mapListDto(any());
    }

    @Test
    @DisplayName("находить все заметки по дате до")
    public void findAllNotesByParametersAllNotesByToDateTest() {
        when(noteMapper.mapListDto(any())).thenReturn(randomListNoteDtoRS);

        LocalDateTime dateTime = LocalDateTime.of(2022, Month.APRIL, 1, 4, 6);

        List<NoteDtoRS> allNotes = noteService.findAllNotesByParameters(null, dateTime, null, 0, 5);

        Map<String, Object> params = new HashMap<>();
        params.put("toDate", dateTime);
        assertEquals(5, allNotes.size());

        verify(noteRepositoryDecorator, times(1)).findAllByParameters(params, 0, 5);
        verify(noteMapper, times(1)).mapListDto(any());
    }

    @Test
    @DisplayName("находить все заметки по периоду")
    public void findAllNotesByParametersAllNotesByPeriodTest() {
        when(noteMapper.mapListDto(any())).thenReturn(randomListNoteDtoRS);
        LocalDateTime from = LocalDateTime.of(2022, Month.APRIL, 3, 7, 0);
        LocalDateTime to = LocalDateTime.of(2022, Month.MAY, 3, 7, 0);
        Dates dates = Dates.builder()
                .beginDateTime(from)
                .endDateTime(to)
                .build();
        when(dateTimeStrategyHolder.getDateTimeByStrategy(PeriodRQ.TODAY.toString())).thenReturn(dates);

        List<NoteDtoRS> allNotes = noteService.findAllNotesByParameters(PeriodRQ.TODAY, null, 0, 5);

        assertEquals(5, allNotes.size());

        Map<String, Object> params = new HashMap<>();
        params.put("fromDate", from);
        params.put("toDate", to);

        verify(noteRepositoryDecorator, times(1)).findAllByParameters(params, 0, 5);
        verify(noteMapper, times(1)).mapListDto(any());
    }

    @Test
    @DisplayName("находить все заметки по периоду и тэгу")
    public void findAllNotesByParametersAllNotesByPeriodAndTagTest() {
        when(noteMapper.mapListDto(any())).thenReturn(randomListNoteDtoRS);
        LocalDateTime from = LocalDateTime.of(2022, Month.APRIL, 3, 7, 0);
        LocalDateTime to = LocalDateTime.of(2022, Month.MAY, 3, 7, 0);
        Dates dates = Dates.builder()
                .beginDateTime(from)
                .endDateTime(to)
                .build();
        when(dateTimeStrategyHolder.getDateTimeByStrategy(PeriodRQ.TODAY.toString())).thenReturn(dates);

        List<NoteDtoRS> allNotes = noteService.findAllNotesByParameters(PeriodRQ.TODAY, "#tag", 0, 5);

        assertEquals(5, allNotes.size());

        Map<String, Object> params = new HashMap<>();
        params.put("fromDate", from);
        params.put("toDate", to);
        params.put("tag", "#tag");

        verify(noteRepositoryDecorator, times(1)).findAllByParameters(params, 0, 5);
        verify(noteMapper, times(1)).mapListDto(any());
    }

}