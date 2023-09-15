package ru.vckazakova.notebot.repository;

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

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DisplayName("NoteRepositoryDecoratorImpl should:")
class NoteRepositoryDecoratorImplTest extends IntegrationBased {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private NoteRepositoryDecorator noteRepositoryDecorator;

    @BeforeEach
    public void init() {
        Note note = new Note("1", "#здоровье", "чтобы быть здоровым, пей витамины", LocalDateTime.now());
        Note note2 = new Note("2", "#films", "The best film", LocalDateTime.of(2023, Month.AUGUST, 10, 12, 3));
        noteRepository.save(note);
        noteRepository.save(note2);
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

}