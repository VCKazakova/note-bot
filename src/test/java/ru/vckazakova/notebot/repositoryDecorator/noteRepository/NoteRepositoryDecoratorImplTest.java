package ru.vckazakova.notebot.repositoryDecorator.noteRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.vckazakova.notebot.repositoryDecorator.IntegrationBased;
import ru.vckazakova.notebot.repositoryDecorator.noteRepository.repository.entity.NoteEntity;
import ru.vckazakova.notebot.repositoryDecorator.noteRepository.repository.NoteRepository;

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
        NoteEntity noteEntity = new NoteEntity("1", "#здоровье", "чтобы быть здоровым, пей витамины", LocalDateTime.now());
        NoteEntity
                noteEntity2 = new NoteEntity("2", "#films", "The best film", LocalDateTime.of(2023, Month.AUGUST, 10, 12, 3));
        noteRepository.save(noteEntity);
        noteRepository.save(noteEntity2);
    }

    @AfterEach
    public void flush() {
        noteRepository.deleteAll();
    }

    @Test
    @DisplayName("сохранять заметку")
    public void saveNoteTest() {
        NoteEntity noteEntity = new NoteEntity("3", "#java", "OOP - the most important principles", LocalDateTime.now());
        noteRepositoryDecorator.saveNote(noteEntity);
        Optional<NoteEntity> byId = noteRepository.findById("3");
        assertTrue(byId.isPresent());
        assertEquals("#java", byId.get().getTag());
    }

    @Test
    @DisplayName("искать заметки за текущий день")
    public void findNotesForCurrentDayTest() {
        List<NoteEntity> notesForCurrentDay = noteRepositoryDecorator.findNotesForCurrentDay();
        assertEquals(1, notesForCurrentDay.size());
    }

    @Test
    @DisplayName("искать заметки за текущий день по тэгу")
    public void findNotesForCurrentDayByTagTest() {
        List<NoteEntity> notesForCurrentDayByTag = noteRepositoryDecorator.findNotesForCurrentDayByTag("#здоровье");
        assertEquals(1, notesForCurrentDayByTag.size());
    }


    @Test
    @DisplayName("искать заметки за текущую неделю")
    public void findNotesForCurrentWeekTest() {
        List<NoteEntity> notesForCurrentWeek = noteRepositoryDecorator.findNotesForCurrentWeek();
        assertEquals(1, notesForCurrentWeek.size());
    }

    @Test
    @DisplayName("искать заметки за текущую неделю по тэгу")
    public void findNotesForCurrentWeekByTagTest() {
        List<NoteEntity> notesForCurrentWeek = noteRepositoryDecorator.findNotesForCurrentWeekByTag("#art");
        assertEquals(0, notesForCurrentWeek.size());
    }

    @Test
    @DisplayName("искать заметки за текущий месяц")
    public void findNotesForCurrentMonthTest() {
        List<NoteEntity> notesForCurrentMonth = noteRepositoryDecorator.findNotesForCurrentMonth();
        assertEquals(1, notesForCurrentMonth.size());
    }

    @Test
    @DisplayName("искать заметки за текущий месяц по тэгу")
    public void findNotesForCurrentMonthByTagTest() {
        List<NoteEntity> notesForCurrentMonth = noteRepositoryDecorator.findNotesForCurrentMonthByTag("#здоровье");
        assertEquals(1, notesForCurrentMonth.size());
    }

    @Test
    @DisplayName("найти все заметки")
    public void findAllNotesTest() {
        List<NoteEntity> allNoteEntities = noteRepositoryDecorator.findAllNotes();
        assertEquals(2, allNoteEntities.size());
    }

    @Test
    @DisplayName("найти все заметки за период")
    public void findAllNotesByPeriodTest() {
        List<NoteEntity> allNotesByPeriod =
                noteRepositoryDecorator.findAllNotesByPeriod(startDate,
                        endDate);
        assertEquals(1, allNotesByPeriod.size());
    }

    @Test
    @DisplayName("найти все заметки за период по тэгу")
    public void findAllNotesByPeriodAndTagTest() {
        List<NoteEntity> allNotesByPeriodAndTag =
                noteRepositoryDecorator.findAllNotesByPeriodAndTag(startDate, endDate, "#здоровье");
        assertTrue(allNotesByPeriodAndTag.isEmpty());
    }

    @Test
    @DisplayName("найти все заметки по тэгу")
    public void findAllNotesByTagTest() {
        List<NoteEntity> notesByTag = noteRepositoryDecorator.findAllNotesByTag("#films");
        assertEquals(1, notesByTag.size());
    }

    @Test
    @DisplayName("найти все заметки по году")
    public void findAllNotesByYearTest() {
        List<NoteEntity> allNotesByYear = noteRepositoryDecorator.findAllNotesByYear(2023);
        assertFalse(allNotesByYear.isEmpty());
    }

    @Test
    @DisplayName("найти все заметки по году и тэгу")
    public void findAllNotesByYearAndTagTest() {
        List<NoteEntity> notesByYearAndTag = noteRepositoryDecorator.findAllNotesByYearAndTag(2023, "#films");
        List<NoteEntity> all = noteRepository.findAll();
        assertEquals(1, notesByYearAndTag.size());
    }

}