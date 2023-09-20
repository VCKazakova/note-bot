package ru.vckazakova.notebot.repositoryDecorator.noteRepository;

import ru.vckazakova.notebot.repositoryDecorator.noteRepository.repository.entity.NoteEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface NoteRepositoryDecorator {

    void saveNote(NoteEntity noteEntity);

    List<NoteEntity> findNotesForCurrentDay();

    List<NoteEntity> findNotesForCurrentDayByTag(String tag);

    List<NoteEntity> findNotesForCurrentWeek();

    List<NoteEntity> findNotesForCurrentWeekByTag(String tag);

    List<NoteEntity> findNotesForCurrentMonth();

    List<NoteEntity> findNotesForCurrentMonthByTag(String tag);

    List<NoteEntity> findAllNotes();

    List<NoteEntity> findAllNotesByPeriod(LocalDateTime fromDate, LocalDateTime toDate);

    List<NoteEntity> findAllNotesByPeriodAndTag(LocalDateTime fromDate, LocalDateTime toDate, String tag);

    List<NoteEntity> findAllNotesByTag(String tag);

    List<NoteEntity> findAllNotesByYear(int year);

    List<NoteEntity> findAllNotesByYearAndTag(int year, String tag);

}
