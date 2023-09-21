package ru.vckazakova.notebot.note.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vckazakova.notebot.note.dto.NoteDtoRQ;
import ru.vckazakova.notebot.note.dto.NoteDtoRS;
import ru.vckazakova.notebot.repositoryDecorator.noteRepository.NoteRepositoryDecorator;
import ru.vckazakova.notebot.repositoryDecorator.noteRepository.repository.entity.NoteEntity;
import ru.vckazakova.notebot.utils.ObjectIdUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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
    public List<NoteDtoRS> findAllNotesByParameters(LocalDateTime fromDate, LocalDateTime toDate, String tag) {
        log.info("Поиск всех заметок по параметрам fromDate={}, toDate={}, tag={}", fromDate, toDate, tag);
        HashMap<String, Object> parameters = new HashMap<>();
        Optional.ofNullable(fromDate).ifPresent(localDateTime -> parameters.put("toDate", fromDate));
        Optional.ofNullable(toDate).ifPresent(localDateTime -> parameters.put("fromDate", toDate));
        Optional.ofNullable(tag).ifPresent(stringTag -> parameters.put("tag", tag));
        List<NoteEntity> allByParameters = noteRepository.findAllByParameters(parameters);
        return noteMapper.mapListDto(allByParameters);
    }
}
