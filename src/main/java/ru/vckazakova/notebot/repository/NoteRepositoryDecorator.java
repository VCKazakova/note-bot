package ru.vckazakova.notebot.repository;

import ru.vckazakova.notebot.model.Note;

import java.time.LocalDateTime;
import java.util.List;

public interface NoteRepositoryDecorator {

    void saveNote(Note note);

    List<Note> findNotesForCurrentDay();

    List<Note> findNotesForCurrentDayByTag(String tag);

    List<Note> findNotesForCurrentWeek();

    List<Note> findNotesForCurrentWeekByTag(String tag);

    List<Note> findNotesForCurrentMonth();

    List<Note> findNotesForCurrentMonthByTag(String tag);

    List<Note> findAllNotes();

    List<Note> findAllNotesByPeriod(LocalDateTime fromDate, LocalDateTime toDate);

    List<Note> findAllNotesByPeriodAndTag(LocalDateTime fromDate, LocalDateTime toDate, String tag);

    List<Note> findAllNotesByTag(String tag);

    List<Note> findAllNotesByYear(int year);

    List<Note> findAllNotesByYearAndTag(int year, String tag);

}
