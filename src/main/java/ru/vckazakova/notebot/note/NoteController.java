package ru.vckazakova.notebot.note;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.vckazakova.notebot.note.dto.NoteDtoRQ;
import ru.vckazakova.notebot.note.dto.NoteDtoRS;
import ru.vckazakova.notebot.note.service.NoteService;

import java.time.LocalDateTime;
import java.util.List;

@Validated
@RequestMapping("/v1/notes")
@RestController
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public String createNote(@RequestBody @Valid NoteDtoRQ noteDtoRQ) {
        return noteService.createNote(noteDtoRQ);

    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<NoteDtoRS> findAllNotesByParameters(@PathVariable(required = false) LocalDateTime fromDate,
                                                    @PathVariable(required = false) LocalDateTime toDate,
                                                    @PathVariable(required = false) String tag) {
        return noteService.findAllNotesByParameters(fromDate, toDate, tag);
    }

//    @GetMapping()
//    public ResponseEntity<List<NoteDtoRS>> findNotesByParameters(@PathVariable(required = false) String period,
//                                                                 @PathVariable(required = false) String tag) {
//        List<NoteDtoRS> notesByPeriod = noteService.findNotesByPeriod(fromDate, toDate);
//        return ResponseEntity.ok(notesByPeriod);
//    }

}
