package ru.vckazakova.notebot.service;

import ru.vckazakova.notebot.dto.NoteDto;

import java.util.List;

public interface NoteService {

    String createNote(NoteDto noteDto);

    List<NoteDto> findAllNotes();

    List<NoteDto> findTodayNotes();

    List<NoteDto> findWeekNotes();

    List<NoteDto> findMonthNotes();

    List<NoteDto> findYearNotes();

    List<NoteDto> findNotesByPeriod(String fromDate, String toDate);

    List<NoteDto> findNotesByTag(String tag);

    List<NoteDto> findTodayNotesByTag(String tag);

    List<NoteDto> findWeekNotesByTag(String tag);

    List<NoteDto> findMonthNotesByTag(String tag);

    List<NoteDto> findYearNotesByTag(String tag);

    List<NoteDto> findNotesByPeriodAndByTag(String fromDate, String toDate, String tag);

}
