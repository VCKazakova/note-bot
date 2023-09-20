package ru.vckazakova.notebot.repositoryDecorator.noteRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.vckazakova.notebot.repositoryDecorator.noteRepository.repository.entity.NoteEntity;
import ru.vckazakova.notebot.repositoryDecorator.noteRepository.repository.NoteRepository;
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
    public void saveNote(NoteEntity noteEntity) {
        noteRepository.save(noteEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NoteEntity> findNotesForCurrentDay() {
        LocalDateTime startOfCurrentDay = DateTimeUtils.getStartOfCurrentDay();
        LocalDateTime endOfCurrentDay = DateTimeUtils.getEndOfCurrentDay();
        return noteRepository.findAllByDateTimeBetween(startOfCurrentDay, endOfCurrentDay);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NoteEntity> findNotesForCurrentDayByTag(String tag) {
        List<NoteEntity> notesForCurrentDay = findNotesForCurrentDay();
        return this.filterNotesByTag(tag, notesForCurrentDay);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NoteEntity> findNotesForCurrentWeek() {
        LocalDateTime startOfCurrentWeek = DateTimeUtils.getStartOfCurrentWeek();
        LocalDateTime endOfCurrentWeek = DateTimeUtils.getEndOfCurrentWeek();
        return noteRepository.findAllByDateTimeBetween(startOfCurrentWeek, endOfCurrentWeek);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NoteEntity> findNotesForCurrentWeekByTag(String tag) {
        List<NoteEntity> notesForCurrentWeek = findNotesForCurrentWeek();
        return this.filterNotesByTag(tag, notesForCurrentWeek);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NoteEntity> findNotesForCurrentMonth() {
        LocalDateTime startOfCurrentMonth = DateTimeUtils.getStartOfCurrentMonth();
        LocalDateTime endOfCurrentMonth = DateTimeUtils.getEndOfCurrentMonth();
        return noteRepository.findAllByDateTimeBetween(startOfCurrentMonth, endOfCurrentMonth);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NoteEntity> findNotesForCurrentMonthByTag(String tag) {
        List<NoteEntity> notesForCurrentMonth = findNotesForCurrentMonth();
        return filterNotesByTag(tag, notesForCurrentMonth);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NoteEntity> findAllNotes() {
        return noteRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<NoteEntity> findAllNotesByPeriod(LocalDateTime fromDate, LocalDateTime toDate) {
        return noteRepository.findAllByDateTimeBetween(fromDate, toDate);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NoteEntity> findAllNotesByPeriodAndTag(LocalDateTime fromDate, LocalDateTime toDate, String tag) {
        return noteRepository.findAllByDateTimeBetweenAndTag(fromDate, toDate, tag);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NoteEntity> findAllNotesByTag(String tag) {
        return noteRepository.findAllByTag(tag);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NoteEntity> findAllNotesByYear(int year) {
        return noteRepository.findAllByYear(year);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NoteEntity> findAllNotesByYearAndTag(int year, String tag) {
        return noteRepository.findAllByYearAndTag(year, tag);
    }

    private List<NoteEntity> filterNotesByTag(String tag, List<NoteEntity> noteEntities) {
        return noteEntities.stream()
                .filter(note -> tag.equals(note.getTag()))
                .collect(Collectors.toList());
    }

}
