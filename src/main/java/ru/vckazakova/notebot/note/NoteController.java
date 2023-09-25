package ru.vckazakova.notebot.note;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.vckazakova.notebot.note.dto.NoteDtoRQ;
import ru.vckazakova.notebot.note.dto.NoteDtoRS;
import ru.vckazakova.notebot.note.dto.PeriodRQ;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/v1/notes")
public interface NoteController {

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    String createNote(@RequestBody @Valid NoteDtoRQ noteDtoRQ);

    @GetMapping
    List<NoteDtoRS> findAllNotesByParameters(@RequestParam(required = false) LocalDateTime fromDate,
                                             @RequestParam(required = false) LocalDateTime toDate,
                                             @RequestParam(required = false) String tag,
                                             @RequestParam(required = false, defaultValue = "0") int page,
                                             @RequestParam(required = false, defaultValue = "5") int size);

    @GetMapping("/byPeriod")
    List<NoteDtoRS> findNotesByParameters(@RequestParam(required = false) PeriodRQ period,
                                          @RequestParam(required = false) String tag,
                                          @RequestParam(required = false, defaultValue = "0") int page,
                                          @RequestParam(required = false, defaultValue = "5") int size);

}
