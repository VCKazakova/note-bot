package ru.vckazakova.notebot.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.vckazakova.notebot.model.Note;
import ru.vckazakova.notebot.utils.DateTimeUtils;

import java.time.*;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class NoteRepositoryDecoratorImpl implements NoteRepositoryDecorator {

    private final NoteRepository noteRepository;

    @Override
    public List<Note> findNotesForCurrentDay() {
        LocalDateTime startOfCurrentDay = DateTimeUtils.getStartOfCurrentDay();
        LocalDateTime endOfCurrentDay = DateTimeUtils.getEndOfCurrentDay();
        return noteRepository.findAllByDateTimeBetween(startOfCurrentDay, endOfCurrentDay);
    }

    @Override
    public List<Note> findNotesForCurrentDayByTag(String tag) {
        List<Note> notesForCurrentDay = findNotesForCurrentDay();
        return this.filterNotesByTag(tag, notesForCurrentDay);
    }

    @Override
    public List<Note> findNotesForCurrentWeek() {
        LocalDateTime startOfCurrentWeek = DateTimeUtils.getStartOfCurrentWeek();
        LocalDateTime endOfCurrentWeek = DateTimeUtils.getEndOfCurrentWeek();
        return noteRepository.findAllByDateTimeBetween(startOfCurrentWeek, endOfCurrentWeek);
    }

    @Override
    public List<Note> findNotesForCurrentWeekByTag(String tag) {
        List<Note> notesForCurrentWeek = findNotesForCurrentWeek();
        return this.filterNotesByTag(tag, notesForCurrentWeek);
    }

    @Override
    public List<Note> findNotesForCurrentMonth() {
        LocalDateTime startOfCurrentMonth = DateTimeUtils.getStartOfCurrentMonth();
        LocalDateTime endOfCurrentMonth = DateTimeUtils.getEndOfCurrentMonth();
        return noteRepository.findAllByDateTimeBetween(startOfCurrentMonth, endOfCurrentMonth);
    }

    @Override
    public List<Note> findNotesForCurrentMonthByTag(String tag) {
        List<Note> notesForCurrentMonth = findNotesForCurrentMonth();
        return filterNotesByTag(tag, notesForCurrentMonth);
    }

    private List<Note> filterNotesByTag(String tag, List<Note> notes) {
        return notes.stream()
                .filter(note -> tag.equals(note.getTag()))
                .collect(Collectors.toList());
    }

}
