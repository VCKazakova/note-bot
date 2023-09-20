package ru.vckazakova.notebot.note.service;

import ru.vckazakova.notebot.note.dto.NoteDtoRQ;
import ru.vckazakova.notebot.note.dto.NoteDtoRS;

import java.time.LocalDateTime;
import java.util.List;

public interface NoteService {

    String createNote(NoteDtoRQ noteDtoRQ);

    List<NoteDtoRS> findAllNotes();

    List<NoteDtoRS> findTodayNotes();

    List<NoteDtoRS> findCurrentWeekNotes();

    List<NoteDtoRS> findCurrentMonthNotes();

    List<NoteDtoRS> findYearNotes(int year);

    List<NoteDtoRS> findNotesByPeriod(LocalDateTime fromDate, LocalDateTime toDate);

    List<NoteDtoRS> findNotesByTag(String tag);

    List<NoteDtoRS> findTodayNotesByTag(String tag);

    List<NoteDtoRS> findCurrentWeekNotesByTag(String tag);

    List<NoteDtoRS> findCurrentMonthNotesByTag(String tag);

    List<NoteDtoRS> findYearNotesByTag(int year, String tag);

    List<NoteDtoRS> findNotesByPeriodAndByTag(LocalDateTime fromDate, LocalDateTime toDate, String tag);

}
