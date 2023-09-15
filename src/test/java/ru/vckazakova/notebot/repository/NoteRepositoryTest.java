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
@DisplayName("NoteRepository should:")
class NoteRepositoryTest extends IntegrationBased {

    @Autowired
    private NoteRepository noteRepository;

    @BeforeEach
    public void init() {
        Note the_best_film = new Note("1", "#films", "The best film", LocalDateTime.of(2023, Month.MARCH, 12, 10, 6));
        noteRepository.save(the_best_film);
        Note the_best_film2 = new Note("2", "#films", "The best film2", LocalDateTime.of(2022, Month.MARCH, 2, 11, 3));
        noteRepository.save(the_best_film2);
        Note the_best_game = new Note("3", "#games", "The best game", LocalDateTime.of(2022, Month.APRIL, 25, 12, 6));
        noteRepository.save(the_best_game);
    }

    @AfterEach
    public void flush() {
        noteRepository.deleteAll();
    }

    @Test
    @DisplayName("создавать заметку")
    public void createNoteTest() {
        Note note = new Note("10", "#здоровье", "чтобы быть здоровым, пей витамины", LocalDateTime.now());
        noteRepository.save(note);
        Optional<Note> byId = noteRepository.findById("10");
        assertTrue(byId.isPresent());
        assertEquals("#здоровье", byId.get().getTag());
    }

    @Test
    @DisplayName("найти все заметки")
    public void findAllNotesTest() {
        List<Note> all = noteRepository.findAll();
        assertFalse(all.isEmpty());
    }

    @Test
    @DisplayName("найти все заметки по тэгу")
    public void findAllNotesByTagTest() {
        List<Note> allByTag = noteRepository.findAllByTag("#films");
        assertEquals(2, allByTag.size());
    }

    @Test
    @DisplayName("найти все заметки по году")
    public void findAllNotesByYearTest() {
        List<Note> allByYear = noteRepository.findAllByYear(2023);
        assertEquals(1, allByYear.size());
    }

    @Test
    @DisplayName("найти все заметки по году и тэгу")
    public void findAllNotesByYearAndTagTest() {
        List<Note> allByYearAndTag = noteRepository.findAllByYearAndTag(2023, "#films");
        assertEquals(1, allByYearAndTag.size());
    }

    @Test
    @DisplayName("найти все заметки за период")
    public void findAllByPeriodTest() {
        List<Note> allByDateTimeBetween =
                noteRepository.findAllByDateTimeBetween(LocalDateTime.of(2022, Month.MARCH, 1, 10, 0),
                        LocalDateTime.of(2022, Month.APRIL, 30, 10, 0));
        assertEquals(2, allByDateTimeBetween.size());
    }

    @Test
    @DisplayName("найти все заметки за период по тэгу")
    public void findAllByPeriodAndTagTest() {
        List<Note> allByDateTimeBetween =
                noteRepository.findAllByDateTimeBetweenAndTag(LocalDateTime.of(2022, Month.MARCH, 1, 10, 0),
                        LocalDateTime.of(2022, Month.APRIL, 30, 10, 0), "#films");
        assertEquals(1, allByDateTimeBetween.size());
    }

}