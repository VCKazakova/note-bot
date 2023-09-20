package ru.vckazakova.notebot.note.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vckazakova.notebot.note.dto.NoteDtoRQ;
import ru.vckazakova.notebot.note.dto.NoteDtoRS;
import ru.vckazakova.notebot.repositoryDecorator.noteRepository.repository.entity.NoteEntity;
import ru.vckazakova.notebot.repositoryDecorator.noteRepository.NoteRepositoryDecorator;
import ru.vckazakova.notebot.utils.ObjectIdUtils;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepositoryDecorator noteRepository;
    private final NoteMapper noteMapper;

    @Override
    public String createNote(NoteDtoRQ noteDtoRQ) {
        LocalDateTime createDateTime = LocalDateTime.now();
        String text = noteDtoRQ.getText();
        log.info("Создание заметки = {} ", text);
        NoteEntity noteEntity = noteMapper.mapNote(noteDtoRQ);
        String tagId = ObjectIdUtils.createId();
        noteEntity.setId(tagId);
        noteEntity.setDateTime(createDateTime);
        noteRepository.saveNote(noteEntity);
        return text + " заметка создана успешно";
    }

    @Override
    public List<NoteDtoRS> findAllNotes() {
        log.info("Поиск всех заметок");
        List<NoteEntity> all = noteRepository.findAllNotes();
        return noteMapper.mapListDto(all);
    }

    @Override
    public List<NoteDtoRS> findTodayNotes() {
        log.info("Поиск заметок за текущий день");
        List<NoteEntity> notesForCurrentDay = noteRepository.findNotesForCurrentDay();
        return noteMapper.mapListDto(notesForCurrentDay);
    }

    @Override
    public List<NoteDtoRS> findCurrentWeekNotes() {
        log.info("Поиск заметок за текущую неделю");
        List<NoteEntity> notesForCurrentWeek = noteRepository.findNotesForCurrentWeek();
        return noteMapper.mapListDto(notesForCurrentWeek);
    }

    @Override
    public List<NoteDtoRS> findCurrentMonthNotes() {
        log.info("Поиск заметок за текущий месяц");
        List<NoteEntity> notesForCurrentMonth = noteRepository.findNotesForCurrentMonth();
        return noteMapper.mapListDto(notesForCurrentMonth);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NoteDtoRS> findYearNotes(int year) {
        log.info("Поиск заметок за {} год", year);
        List<NoteEntity> allByYear = noteRepository.findAllNotesByYear(year);
        return noteMapper.mapListDto(allByYear);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NoteDtoRS> findNotesByPeriod(LocalDateTime fromDate, LocalDateTime toDate) {
        log.info("Поиск заметок за период c {} по {} ", fromDate, toDate);
        List<NoteEntity> allNotesByPeriod = noteRepository.findAllNotesByPeriod(fromDate, toDate);
        return noteMapper.mapListDto(allNotesByPeriod);
    }

    @Override
    public List<NoteDtoRS> findNotesByTag(String tag) {
        log.info("Поиск заметок по тэгу = {} ", tag);
        List<NoteEntity> allByTag = noteRepository.findAllNotesByTag(tag);
        return noteMapper.mapListDto(allByTag);
    }

    @Override
    public List<NoteDtoRS> findTodayNotesByTag(String tag) {
        log.info("Поиск заметок за текущий день по тэгу {} ", tag);
        List<NoteEntity> notesForCurrentDayByTag = noteRepository.findNotesForCurrentDayByTag(tag);
        return noteMapper.mapListDto(notesForCurrentDayByTag);
    }

    @Override
    public List<NoteDtoRS> findCurrentWeekNotesByTag(String tag) {
        log.info("Поиск заметок за текущую неделю по тэгу {} ", tag);
        List<NoteEntity> notesForCurrentWeekByTag = noteRepository.findNotesForCurrentWeekByTag(tag);
        return noteMapper.mapListDto(notesForCurrentWeekByTag);
    }

    @Override
    public List<NoteDtoRS> findCurrentMonthNotesByTag(String tag) {
        log.info("Поиск заметок за текущий месяц по тэгу {} ", tag);
        List<NoteEntity> notesForCurrentMonthByTag = noteRepository.findNotesForCurrentMonthByTag(tag);
        return noteMapper.mapListDto(notesForCurrentMonthByTag);
    }

    @Override
    public List<NoteDtoRS> findYearNotesByTag(int year, String tag) {
        log.info("Поиск заметок по году и тэгу {}; {} ", year, tag);
        List<NoteEntity> allByYearAndTag = noteRepository.findAllNotesByYearAndTag(year, tag);
        return noteMapper.mapListDto(allByYearAndTag);
    }

    @Override
    public List<NoteDtoRS> findNotesByPeriodAndByTag(LocalDateTime fromDate, LocalDateTime toDate, String tag) {
        log.info("Поиск заметок за период с {} по {} с тэгом {} ", fromDate, toDate, tag);
        List<NoteEntity> allNotesByPeriodAndTag = noteRepository.findAllNotesByPeriodAndTag(fromDate, toDate, tag);
        return noteMapper.mapListDto(allNotesByPeriodAndTag);
    }
}
