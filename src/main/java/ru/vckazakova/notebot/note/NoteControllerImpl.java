package ru.vckazakova.notebot.note;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.vckazakova.notebot.note.dto.NoteDtoRQ;
import ru.vckazakova.notebot.note.dto.NoteDtoRS;
import ru.vckazakova.notebot.note.dto.PeriodRQ;
import ru.vckazakova.notebot.note.service.NoteService;
import ru.vckazakova.notebot.utils.validation.DateTimeValidator;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class NoteControllerImpl implements NoteController {

    private final NoteService noteService;
    private final DateTimeValidator dateTimeValidator;

    public String createNote(NoteDtoRQ noteDtoRQ) {
        return noteService.createNote(noteDtoRQ);

    }

    public List<NoteDtoRS> findAllNotesByParameters(LocalDateTime fromDate,
                                                    LocalDateTime toDate,
                                                    String tag,
                                                    int page,
                                                    int size) {
        dateTimeValidator.validatePeriod(fromDate, toDate);
        return noteService.findAllNotesByParameters(fromDate, toDate, tag, page, size);
    }

    public List<NoteDtoRS> findNotesByParameters(PeriodRQ period,
                                                 String tag,
                                                 int page,
                                                 int size) {
        return noteService.findAllNotesByParameters(period, tag, page, size);
    }

}
