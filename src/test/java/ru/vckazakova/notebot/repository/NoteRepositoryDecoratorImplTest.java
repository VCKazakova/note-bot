package ru.vckazakova.notebot.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.vckazakova.notebot.IntegrationBased;
import ru.vckazakova.notebot.model.Note;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("NoteRepositoryDecoratorImpl should:")
class NoteRepositoryDecoratorImplTest extends IntegrationBased {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private NoteRepositoryDecorator noteRepositoryDecorator;

    private final LocalDateTime startDate = LocalDateTime.of(2023, Month.AUGUST, 1, 0, 0);
    private final LocalDateTime endDate = LocalDateTime.of(2023, Month.AUGUST, 31, 23, 59);

    @BeforeEach
    public void init() {
        Note note = new Note("1", "#здоровье", "чтобы быть здоровым, пей витамины", LocalDateTime.now());
        Note note2 = new Note("2", "#films", "The best film", LocalDateTime.of(2023, Month.AUGUST, 10, 12, 3));
        noteRepository.save(note);
        noteRepository.save(note2);
    }

    @AfterEach
    public void flush() {
        noteRepository.deleteAll();
    }

    @Test
    @DisplayName("сохранять заметку")
    public void saveNoteTest() {
        Note note = new Note("3", "#java", "OOP - the most important principles", LocalDateTime.now());
        noteRepositoryDecorator.saveNote(note);
        Optional<Note> byId = noteRepository.findById("3");
        assertTrue(byId.isPresent());
        assertEquals("#java", byId.get().getTag());
    }

    @Test
    @DisplayName("искать заметки за текущий день")
    public void findNotesForCurrentDayTest() {
        List<Note> notesForCurrentDay = noteRepositoryDecorator.findNotesForCurrentDay();
        assertEquals(1, notesForCurrentDay.size());
    }

    @Test
    @DisplayName("искать заметки за текущий день по тэгу")
    public void findNotesForCurrentDayByTagTest() {
        List<Note> notesForCurrentDayByTag = noteRepositoryDecorator.findNotesForCurrentDayByTag("#здоровье");
        assertEquals(1, notesForCurrentDayByTag.size());
    }


    @Test
    @DisplayName("искать заметки за текущую неделю")
    public void findNotesForCurrentWeekTest() {
        List<Note> notesForCurrentWeek = noteRepositoryDecorator.findNotesForCurrentWeek();
        assertEquals(1, notesForCurrentWeek.size());
    }

    @Test
    @DisplayName("искать заметки за текущую неделю по тэгу")
    public void findNotesForCurrentWeekByTagTest() {
        List<Note> notesForCurrentWeek = noteRepositoryDecorator.findNotesForCurrentWeekByTag("#art");
        assertEquals(0, notesForCurrentWeek.size());
    }

    @Test
    @DisplayName("искать заметки за текущий месяц")
    public void findNotesForCurrentMonthTest() {
        List<Note> notesForCurrentMonth = noteRepositoryDecorator.findNotesForCurrentMonth();
        assertEquals(1, notesForCurrentMonth.size());
    }

    @Test
    @DisplayName("искать заметки за текущий месяц по тэгу")
    public void findNotesForCurrentMonthByTagTest() {
        List<Note> notesForCurrentMonth = noteRepositoryDecorator.findNotesForCurrentMonthByTag("#здоровье");
        assertEquals(1, notesForCurrentMonth.size());
    }

    @Test
    @DisplayName("найти все заметки")
    public void findAllNotesTest() {
        List<Note> allNotes = noteRepositoryDecorator.findAllNotes();
        assertEquals(2, allNotes.size());
    }

    @Test
    @DisplayName("найти все заметки за период")
    public void findAllNotesByPeriodTest() {
        List<Note> allNotesByPeriod =
                noteRepositoryDecorator.findAllNotesByPeriod(startDate,
                        endDate);
        assertEquals(1, allNotesByPeriod.size());
    }

    @Test
    @DisplayName("найти все заметки за период по тэгу")
    public void findAllNotesByPeriodAndTagTest() {
        List<Note> allNotesByPeriodAndTag =
                noteRepositoryDecorator.findAllNotesByPeriodAndTag(startDate, endDate, "#здоровье");
        assertTrue(allNotesByPeriodAndTag.isEmpty());
    }

    @Test
    @DisplayName("найти все заметки по тэгу")
    public void findAllNotesByTagTest() {
        List<Note> notesByTag = noteRepositoryDecorator.findAllNotesByTag("#films");
        assertEquals(1, notesByTag.size());
    }

    @Test
    @DisplayName("найти все заметки по году")
    public void findAllNotesByYearTest() {
        List<Note> allNotesByYear = noteRepositoryDecorator.findAllNotesByYear(2023);
        assertFalse(allNotesByYear.isEmpty());
    }

    @Test
    @DisplayName("найти все заметки по году и тэгу")
    public void findAllNotesByYearAndTagTest() {
        List<Note> notesByYearAndTag = noteRepositoryDecorator.findAllNotesByYearAndTag(2023, "#films");
        List<Note> all = noteRepository.findAll();
        assertEquals(1, notesByYearAndTag.size());
    }

}