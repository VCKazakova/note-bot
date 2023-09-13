package ru.vckazakova.notebot.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.vckazakova.notebot.IntegrationBased;
import ru.vckazakova.notebot.model.Note;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@DisplayName("NoteRepository should:")
class NoteRepositoryTest extends IntegrationBased {

    @Autowired
    private NoteRepository noteRepository;

    private final Note note = new Note("1", "#здоровье", "чтобы быть здоровым, пей витамины", LocalDateTime.now());

    @Test
    @DisplayName("создавать заметку")
    public void createNoteTest() {
        noteRepository.save(note);
        Optional<Note> byId = noteRepository.findById("1");
        assertTrue(byId.isPresent());
    }

    @Test
    @DisplayName("найти все заметки")
    public void findAllNotesTest() {
        noteRepository.save(note);
        List<Note> all = noteRepository.findAll();
        assertFalse(all.isEmpty());
    }

}