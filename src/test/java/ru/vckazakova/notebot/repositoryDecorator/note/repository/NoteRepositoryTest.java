package ru.vckazakova.notebot.repositoryDecorator.note.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.vckazakova.notebot.repositoryDecorator.IntegrationBased;
import ru.vckazakova.notebot.repositoryDecorator.note.repository.entity.NoteEntity;

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

    @Test
    @DisplayName("создавать заметку")
    public void createNoteTest() {
        NoteEntity noteEntity = new NoteEntity("10", "#здоровье", "чтобы быть здоровым, пей витамины", LocalDateTime.now());
        noteRepository.save(noteEntity);
        Optional<NoteEntity> byId = noteRepository.findById("10");
        assertTrue(byId.isPresent());
        assertEquals("#здоровье", byId.get().getTag());
    }

    @Test
    @DisplayName("найти все заметки")
    public void findAllNotesTest() {
        NoteEntity the_best_film =
                new NoteEntity("1", "#films", "The best film", LocalDateTime.of(2023, Month.MARCH, 12, 10, 6));
        noteRepository.save(the_best_film);
        NoteEntity
                the_best_film2 =
                new NoteEntity("2", "#films", "The best film2", LocalDateTime.of(2022, Month.MARCH, 2, 11, 3));
        noteRepository.save(the_best_film2);
        NoteEntity the_best_game =
                new NoteEntity("3", "#games", "The best game", LocalDateTime.of(2022, Month.APRIL, 25, 12, 6));
        noteRepository.save(the_best_game);

        List<NoteEntity> all = noteRepository.findAll();
        assertFalse(all.isEmpty());
    }

}