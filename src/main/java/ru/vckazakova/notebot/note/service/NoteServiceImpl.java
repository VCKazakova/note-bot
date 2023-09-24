package ru.vckazakova.notebot.note.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vckazakova.notebot.note.dto.NoteDtoRQ;
import ru.vckazakova.notebot.note.dto.NoteDtoRS;
import ru.vckazakova.notebot.note.dto.PeriodRQ;
import ru.vckazakova.notebot.note.service.dateTimeHolder.DateTimeStrategyHolder;
import ru.vckazakova.notebot.note.service.dateTimeHolder.dateTimeStrategy.Dates;
import ru.vckazakova.notebot.repositoryDecorator.note.NoteRepositoryDecorator;
import ru.vckazakova.notebot.repositoryDecorator.note.repository.entity.NoteEntity;
import ru.vckazakova.notebot.utils.ObjectIdUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepositoryDecorator noteRepository;
    private final NoteMapper noteMapper;
    private final DateTimeStrategyHolder dateTimeStrategyHolder;

    @Override
    public String createNote(NoteDtoRQ noteDtoRQ) {
        String text = noteDtoRQ.getText();
        log.info("Создание заметки = {} ", text);
        NoteEntity noteEntity = noteMapper.mapNote(noteDtoRQ);
        String tagId = ObjectIdUtils.createId();
        noteEntity.setId(tagId);
        LocalDateTime createDateTime = LocalDateTime.now();
        noteEntity.setDateTime(createDateTime);
        noteRepository.saveNote(noteEntity);
        return text + " заметка создана успешно";
    }

    @Override
    public List<NoteDtoRS> findAllNotesByParameters(LocalDateTime fromDate, LocalDateTime toDate, String tag, int page, int size) {
        log.info("Поиск всех заметок по параметрам fromDate={}, toDate={}, tag={}, page={}, size={}", fromDate, toDate, tag, page, size);
        Map<String, Object> parameters = this.createParamsMap(fromDate, toDate, tag);
        List<NoteEntity> allByParameters = noteRepository.findAllByParameters(parameters, page, size);
        return noteMapper.mapListDto(allByParameters);
    }

    private Map<String, Object> createParamsMap(LocalDateTime fromDate, LocalDateTime toDate, String tag) {
        HashMap<String, Object> parameters = new HashMap<>();
        Optional.ofNullable(fromDate).ifPresent(localDateTime -> parameters.put("toDate", fromDate));
        Optional.ofNullable(toDate).ifPresent(localDateTime -> parameters.put("fromDate", toDate));
        Optional.ofNullable(tag).ifPresent(stringTag -> parameters.put("tag", tag));
        return parameters;
    }

    @Override
    public List<NoteDtoRS> findAllNotesByParameters(PeriodRQ period, String tag, int page, int size) {
        log.info("Поиск всех заметок по параметрам period={}, tag={}, page={}, size={}", period, tag, page, size);
        Dates dates = dateTimeStrategyHolder.getDateTimeByStrategy(period.name());
        // вызвать класс который будет на вход получать период и отдавать dates
        // этот класс будет содержать мапу dateTimeStrategy
        Map<String, Object> params = this.createParamsMap(dates.getBeginDateTime(), dates.getEndDateTime(), tag);
        List<NoteEntity> allByParameters = noteRepository.findAllByParameters(params, page, size);
        return noteMapper.mapListDto(allByParameters);
    }
}
