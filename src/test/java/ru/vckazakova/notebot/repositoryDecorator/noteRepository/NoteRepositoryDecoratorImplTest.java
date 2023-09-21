package ru.vckazakova.notebot.repositoryDecorator.noteRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.vckazakova.notebot.repositoryDecorator.IntegrationBased;
import ru.vckazakova.notebot.repositoryDecorator.noteRepository.repository.NoteRepository;
import ru.vckazakova.notebot.repositoryDecorator.noteRepository.repository.entity.NoteEntity;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public void test() {
        Map<String, Object> params = new HashMap<>();
        params.put("tag", "#films");
        List<NoteEntity> allByParametersCriteria = noteRepositoryDecorator.findAllByParameters(params);
        assertEquals(1, allByParametersCriteria.size());
    }

    @Test
    public void test2() {
        Map<String, Object> params = new HashMap<>();
        params.put("tag", "#films");
        params.put("fromDate", startDate);
        params.put("toDate", endDate);
        List<NoteEntity> allByParametersCriteria = noteRepositoryDecorator.findAllByParameters(params);
        assertEquals(1, allByParametersCriteria.size());
    }

    @Test
    public void test22() {
        Map<String, Object> params = new HashMap<>();
        List<NoteEntity> allByParametersCriteria = noteRepositoryDecorator.findAllByParameters(params);
        assertEquals(2, allByParametersCriteria.size());
    }

    @Test
    public void test3() {
        // только тэг
        Map<String, Object> params = new HashMap<>();
        params.put("tag", "#films");
        List<NoteEntity> allByParameters = noteRepositoryDecorator.findAllByParameters(params);
        assertEquals(1, allByParameters.size());
    }

    @Test
    public void test4() {
        // без параметров
        Map<String, Object> params = new HashMap<>();
        List<NoteEntity> allByParameters = noteRepositoryDecorator.findAllByParameters(params);
        assertEquals(2, allByParameters.size());
    }

    @Test
    public void test5() {
        // 2 даты
        Map<String, Object> params = new HashMap<>();
        params.put("dateFrom", startDate);
        params.put("dateTo", endDate);
        List<NoteEntity> allByParameters = noteRepositoryDecorator.findAllByParameters(params);
        assertEquals(2, allByParameters.size());
    }

    @Test
    public void test6() {
        // 2 даты + тэг
        Map<String, Object> params = new HashMap<>();
        params.put("dateFrom", startDate);
        params.put("dateTo", endDate);
        params.put("tag", "#films");
        List<NoteEntity> allByParameters = noteRepositoryDecorator.findAllByParameters(params);
        assertEquals(1, allByParameters.size());
    }

    @Test
    public void test7() {
        // 1 дата + тэг
        Map<String, Object> params = new HashMap<>();
        params.put("dateTo", endDate);
        params.put("tag", "#films");
        List<NoteEntity> allByParameters = noteRepositoryDecorator.findAllByParameters(params);
        assertEquals(1, allByParameters.size());
    }

    @Test
    public void test8() {
        // 1 дата
        Map<String, Object> params = new HashMap<>();
        params.put("dateTo", endDate);
        List<NoteEntity> allByParameters = noteRepositoryDecorator.findAllByParameters(params);
        assertEquals(2, allByParameters.size());
    }

}