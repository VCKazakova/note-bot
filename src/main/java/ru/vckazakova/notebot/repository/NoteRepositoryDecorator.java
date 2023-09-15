package ru.vckazakova.notebot.repository;

import ru.vckazakova.notebot.model.Note;

import java.util.List;

public interface NoteRepositoryDecorator {

    List<Note> findNotesForCurrentDay();

    List<Note> findNotesForCurrentDayByTag(String tag);

    List<Note> findNotesForCurrentWeek();

    List<Note> findNotesForCurrentWeekByTag(String tag);

    List<Note> findNotesForCurrentMonth();

    List<Note> findNotesForCurrentMonthByTag(String tag);

}
