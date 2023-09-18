package ru.vckazakova.notebot.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.vckazakova.notebot.dto.NoteDtoRQ;
import ru.vckazakova.notebot.dto.NoteDtoRS;
import ru.vckazakova.notebot.service.NoteService;

import java.time.LocalDateTime;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping("/note")
    public ResponseEntity<String> createNote(@RequestBody @Valid NoteDtoRQ noteDtoRQ) {
        String note = noteService.createNote(noteDtoRQ);
        return ResponseEntity.status(201)
                .body(note);
    }

    @GetMapping("/note")
    public ResponseEntity<List<NoteDtoRS>> findAllNotes() {
        List<NoteDtoRS> allNotes = noteService.findAllNotes();
        return ResponseEntity.ok(allNotes);
    }

    @GetMapping("/note/today")
    public ResponseEntity<List<NoteDtoRS>> findTodayNotes() {
        List<NoteDtoRS> todayNotes = noteService.findTodayNotes();
        return ResponseEntity.status(200)
                .body(todayNotes);
    }

    @GetMapping("/note/currentWeek")
    public ResponseEntity<List<NoteDtoRS>> findCurrentWeekNotes() {
        List<NoteDtoRS> currentWeekNotes = noteService.findCurrentWeekNotes();
        return ResponseEntity.ok(currentWeekNotes);
    }

    @GetMapping("/note/currentMonth")
    public ResponseEntity<List<NoteDtoRS>> findCurrentMonthNotes() {
        List<NoteDtoRS> currentMonthNotes = noteService.findCurrentMonthNotes();
        return ResponseEntity.ok(currentMonthNotes);
    }

    @GetMapping("/note/year/{year}")
    public ResponseEntity<List<NoteDtoRS>> findYearNotes(@PathVariable int year) {
        List<NoteDtoRS> yearNotes = noteService.findYearNotes(year);
        return ResponseEntity.ok(yearNotes);
    }

    @GetMapping("/note/byPeriod")
    public ResponseEntity<List<NoteDtoRS>> findNotesByPeriod(@RequestParam LocalDateTime fromDate, @RequestParam LocalDateTime toDate) {
        List<NoteDtoRS> notesByPeriod = noteService.findNotesByPeriod(fromDate, toDate);
        return ResponseEntity.ok(notesByPeriod);
    }

    @GetMapping("/note/{tag}")
    public ResponseEntity<List<NoteDtoRS>> findNotesByTag(@PathVariable String tag) {
        List<NoteDtoRS> notesByTag = noteService.findNotesByTag(tag);
        return ResponseEntity.ok(notesByTag);
    }

    @GetMapping("/note/today/{tag}")
    public ResponseEntity<List<NoteDtoRS>> findTodayNotesByTag(@PathVariable String tag) {
        List<NoteDtoRS> todayNotesByTag = noteService.findTodayNotesByTag(tag);
        return ResponseEntity.status(200)
                .body(todayNotesByTag);
    }

    @GetMapping("/note/currentWeek/{tag}")
    public ResponseEntity<List<NoteDtoRS>> findCurrentWeekNotesByTag(@PathVariable String tag) {
        List<NoteDtoRS> currentWeekNotesByTag = noteService.findCurrentWeekNotesByTag(tag);
        return ResponseEntity.ok(currentWeekNotesByTag);
    }

    @GetMapping("/note/currentMonth/{tag}")
    public ResponseEntity<List<NoteDtoRS>> findCurrentMonthNotesByTag(@PathVariable String tag) {
        List<NoteDtoRS> currentMonthNotesByTag = noteService.findCurrentMonthNotesByTag(tag);
        return ResponseEntity.ok(currentMonthNotesByTag);
    }

    @GetMapping("/note/{year}/{tag}")
    public ResponseEntity<List<NoteDtoRS>> findYearNotesByTag(@PathVariable int year, @PathVariable String tag) {
        List<NoteDtoRS> yearNotesByTag = noteService.findYearNotesByTag(year, tag);
        return ResponseEntity.ok(yearNotesByTag);
    }

    @GetMapping("/note/byPeriod/{tag}")
    public ResponseEntity<List<NoteDtoRS>> findNotesByPeriodAndByTag(@RequestParam LocalDateTime fromDate, @RequestParam LocalDateTime toDate, @PathVariable String tag) {
        List<NoteDtoRS> notesByPeriodAndByTag = noteService.findNotesByPeriodAndByTag(fromDate, toDate, tag);
        return ResponseEntity.ok(notesByPeriodAndByTag);
    }

}
