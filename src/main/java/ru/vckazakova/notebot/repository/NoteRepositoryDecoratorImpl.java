package ru.vckazakova.notebot.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.vckazakova.notebot.model.Note;
import ru.vckazakova.notebot.utils.DateTimeUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class NoteRepositoryDecoratorImpl implements NoteRepositoryDecorator {

    private final NoteRepository noteRepository;

    @Override
    @Transactional
    public void saveNote(Note note) {
        noteRepository.save(note);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Note> findNotesForCurrentDay() {
        LocalDateTime startOfCurrentDay = DateTimeUtils.getStartOfCurrentDay();
        LocalDateTime endOfCurrentDay = DateTimeUtils.getEndOfCurrentDay();
        return noteRepository.findAllByDateTimeBetween(startOfCurrentDay, endOfCurrentDay);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Note> findNotesForCurrentDayByTag(String tag) {
        List<Note> notesForCurrentDay = findNotesForCurrentDay();
        return this.filterNotesByTag(tag, notesForCurrentDay);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Note> findNotesForCurrentWeek() {
        LocalDateTime startOfCurrentWeek = DateTimeUtils.getStartOfCurrentWeek();
        LocalDateTime endOfCurrentWeek = DateTimeUtils.getEndOfCurrentWeek();
        return noteRepository.findAllByDateTimeBetween(startOfCurrentWeek, endOfCurrentWeek);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Note> findNotesForCurrentWeekByTag(String tag) {
        List<Note> notesForCurrentWeek = findNotesForCurrentWeek();
        return this.filterNotesByTag(tag, notesForCurrentWeek);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Note> findNotesForCurrentMonth() {
        LocalDateTime startOfCurrentMonth = DateTimeUtils.getStartOfCurrentMonth();
        LocalDateTime endOfCurrentMonth = DateTimeUtils.getEndOfCurrentMonth();
        return noteRepository.findAllByDateTimeBetween(startOfCurrentMonth, endOfCurrentMonth);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Note> findNotesForCurrentMonthByTag(String tag) {
        List<Note> notesForCurrentMonth = findNotesForCurrentMonth();
        return filterNotesByTag(tag, notesForCurrentMonth);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Note> findAllNotes() {
        return noteRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Note> findAllNotesByPeriod(LocalDateTime fromDate, LocalDateTime toDate) {
        return noteRepository.findAllByDateTimeBetween(fromDate, toDate);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Note> findAllNotesByPeriodAndTag(LocalDateTime fromDate, LocalDateTime toDate, String tag) {
        return noteRepository.findAllByDateTimeBetweenAndTag(fromDate, toDate, tag);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Note> findAllNotesByTag(String tag) {
        return noteRepository.findAllByTag(tag);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Note> findAllNotesByYear(int year) {
        return noteRepository.findAllByYear(year);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Note> findAllNotesByYearAndTag(int year, String tag) {
        return noteRepository.findAllByYearAndTag(year, tag);
    }

    private List<Note> filterNotesByTag(String tag, List<Note> notes) {
        return notes.stream()
                .filter(note -> tag.equals(note.getTag()))
                .collect(Collectors.toList());
    }

}
