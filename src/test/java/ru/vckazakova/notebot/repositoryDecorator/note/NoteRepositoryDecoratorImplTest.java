package ru.vckazakova.notebot.repositoryDecorator.note;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.vckazakova.notebot.repositoryDecorator.IntegrationBased;
import ru.vckazakova.notebot.repositoryDecorator.note.repository.NoteRepository;
import ru.vckazakova.notebot.repositoryDecorator.note.repository.entity.NoteEntity;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional(transactionManager = "transactionManager")
@DisplayName("NoteRepositoryDecoratorImpl should:")
class NoteRepositoryDecoratorImplTest extends IntegrationBased {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private NoteRepositoryDecorator noteRepositoryDecorator;

    private final LocalDateTime startDate = LocalDateTime.of(2023, Month.AUGUST, 1, 0, 0);
    private final LocalDateTime endDate = LocalDateTime.of(2023, Month.AUGUST, 31, 23, 59);

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
    @DisplayName("найти все заметки по тэгу")
    public void findAllByParametersByTagTest() {
        NoteEntity
                noteEntity =
                new NoteEntity("2", "#films", "The best film", LocalDateTime.of(2023, Month.AUGUST, 10, 12, 3));
        noteRepository.save(noteEntity);
        NoteEntity
                noteEntity2 = new NoteEntity("3", "#films", "The film", LocalDateTime.of(2023, Month.MAY, 5, 13, 3));
        noteRepository.save(noteEntity2);
        Map<String, Object> params = new HashMap<>();
        params.put("tag", "#films");
        List<NoteEntity> allByParametersCriteria = noteRepositoryDecorator.findAllByParameters(params, 0, 3);
        assertEquals(2, allByParametersCriteria.size());
    }

    @Test
    @DisplayName("найти все заметки по тэгу и периоду")
    public void findAllByParametersByTagAndPeriodTest() {
        NoteEntity
                noteEntity2 =
                new NoteEntity("2", "#films", "The best film", LocalDateTime.of(2023, Month.AUGUST, 10, 12, 3));
        noteRepository.save(noteEntity2);
        Map<String, Object> params = new HashMap<>();
        params.put("tag", "#films");
        params.put("fromDate", startDate);
        params.put("toDate", endDate);
        List<NoteEntity> allByParametersCriteria = noteRepositoryDecorator.findAllByParameters(params, 0, 3);
        assertEquals(1, allByParametersCriteria.size());
    }

    @Test
    @DisplayName("найти все заметки")
    public void findAllByParametersTest() {
        NoteEntity
                noteEntity =
                new NoteEntity("2", "#films", "The best film", LocalDateTime.of(2023, Month.AUGUST, 10, 12, 3));
        noteRepository.save(noteEntity);
        NoteEntity
                noteEntity2 = new NoteEntity("3", "#здоровье", "Пейте витамины, будете здоровы",
                LocalDateTime.of(2023, Month.MAY, 5, 13, 3));
        noteRepository.save(noteEntity2);
        Map<String, Object> params = new HashMap<>();
        List<NoteEntity> allByParametersCriteria = noteRepositoryDecorator.findAllByParameters(params, 0, 3);
        assertEquals(2, allByParametersCriteria.size());
    }

    @Test
    @DisplayName("найти все заметки по периоду")
    public void findAllByParametersByPeriodTest() {
        NoteEntity
                noteEntity =
                new NoteEntity("2", "#films", "The best film", LocalDateTime.of(2023, Month.AUGUST, 10, 12, 3));
        noteRepository.save(noteEntity);
        NoteEntity
                noteEntity2 = new NoteEntity("3", "#здоровье", "Пейте витамины, будете здоровы",
                LocalDateTime.of(2023, Month.AUGUST, 25, 13, 3));
        noteRepository.save(noteEntity2);
        Map<String, Object> params = new HashMap<>();
        params.put("dateFrom", startDate);
        params.put("dateTo", endDate);
        List<NoteEntity> allByParameters = noteRepositoryDecorator.findAllByParameters(params, 0, 3);
        assertEquals(2, allByParameters.size());
    }

    @Test
    @DisplayName("найти все заметки по одной дате и тэгу")
    public void findAllByParametersByDateToAndTagTest() {
        NoteEntity
                noteEntity =
                new NoteEntity("2", "#films", "The best film", LocalDateTime.of(2023, Month.JULY, 10, 12, 3));
        noteRepository.save(noteEntity);
        Map<String, Object> params = new HashMap<>();
        params.put("dateTo", endDate);
        params.put("tag", "#films");
        List<NoteEntity> allByParameters = noteRepositoryDecorator.findAllByParameters(params, 0, 3);
        assertEquals(1, allByParameters.size());
    }

    @Test
    @DisplayName("найти все заметки по одной дате по")
    public void findAllByParametersByDateToTest() {
        NoteEntity
                noteEntity =
                new NoteEntity("2", "#films", "The best film", LocalDateTime.of(2023, Month.JULY, 10, 12, 3));
        noteRepository.save(noteEntity);
        Map<String, Object> params = new HashMap<>();
        params.put("dateTo", endDate);
        List<NoteEntity> allByParameters = noteRepositoryDecorator.findAllByParameters(params, 0, 3);
        assertEquals(1, allByParameters.size());
    }

    @Test
    @DisplayName("найти все заметки по одной дате с")
    public void findAllByParametersByDateFromTest() {
        NoteEntity
                noteEntity =
                new NoteEntity("2", "#films", "The best film", LocalDateTime.of(2023, Month.AUGUST, 10, 12, 3));
        noteRepository.save(noteEntity);
        Map<String, Object> params = new HashMap<>();
        params.put("fromDate", startDate);
        List<NoteEntity> allByParameters = noteRepositoryDecorator.findAllByParameters(params, 0, 3);
        assertEquals(1, allByParameters.size());
    }

    @Test
    @DisplayName("найти все заметки на 2-ой странице")
    public void findAllByParametersOnSecondPageTest() {
        NoteEntity
                noteEntity =
                new NoteEntity("2", "#films", "The best film", LocalDateTime.of(2023, Month.AUGUST, 10, 12, 3));
        noteRepository.save(noteEntity);
        NoteEntity
                noteEntity2 = new NoteEntity("3", "#здоровье", "Пейте витамины, будете здоровы",
                LocalDateTime.of(2023, Month.MAY, 5, 13, 3));
        noteRepository.save(noteEntity2);
        NoteEntity
                noteEntity3 = new NoteEntity("4", "#память", "Больше танцуй, тогда нейронные связи сохранятся",
                LocalDateTime.of(2023, Month.SEPTEMBER, 16, 10, 23));
        noteRepository.save(noteEntity3);
        Map<String, Object> params = new HashMap<>();
        List<NoteEntity> allByParametersCriteria = noteRepositoryDecorator.findAllByParameters(params, 1, 2);
        assertEquals(1, allByParametersCriteria.size());
    }

}