package ru.vckazakova.notebot.note.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.vckazakova.notebot.note.dto.NoteDtoRQ;
import ru.vckazakova.notebot.note.dto.NoteDtoRS;
import ru.vckazakova.notebot.repositoryDecorator.note.NoteRepositoryDecorator;
import ru.vckazakova.notebot.repositoryDecorator.note.repository.entity.NoteEntity;

import java.time.LocalDateTime;
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
    private static List<NoteEntity> randomListNoteEntity;
    private static List<NoteDtoRS> randomListNoteDtoRS;

    @BeforeAll
    public static void init() {
        myFaker = new MyFaker();
        randomListNoteEntity = myFaker.getRandomListNote(5);
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
                new NoteEntity("123", "#art", "Culture is everything that man create", LocalDateTime.now()));

        String note = noteService.createNote(noteDtoRQ);
        assertEquals("Culture is everything that man create заметка создана успешно", note);
        verify(noteRepositoryDecorator, times(1)).saveNote(any());
        verify(noteMapper, times(1)).mapNote(noteDtoRQ);
    }

//    @Test
//    public void test() {
//        when(noteRepositoryDecorator.findAllByParameters(any(), anyInt(), anyInt())).thenReturn(List.of());
//
//
//        List<NoteDtoRS> allNotesByParameters = noteService.findAllNotesByParameters(PeriodRQ.MONTH, null, 0, 3);
//
//        int size = allNotesByParameters.size();
//    }

//    @Test
//    @DisplayName("находить все заметки")
//    public void findAllNotesTest() {
//        when(noteRepositoryDecorator.findAllNotes()).thenReturn(randomListNoteEntity);
//        when(noteMapper.mapListDto(randomListNoteEntity)).thenReturn(randomListNoteDtoRS);
//
//        List<NoteDtoRS> allNotes = noteService.findAllNotes();
//
//        assertEquals(5, allNotes.size());
//
//        verify(noteRepositoryDecorator, times(1)).findAllNotes();
//        verify(noteMapper, times(1)).mapListDto(randomListNoteEntity);
//
//    }

}