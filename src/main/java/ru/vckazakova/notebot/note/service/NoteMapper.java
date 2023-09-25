package ru.vckazakova.notebot.note.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.vckazakova.notebot.note.dto.NoteDtoRQ;
import ru.vckazakova.notebot.note.dto.NoteDtoRS;
import ru.vckazakova.notebot.repositoryDecorator.note.repository.entity.NoteEntity;
import ru.vckazakova.notebot.utils.ObjectIdUtils;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.isNull;

@Mapper(componentModel = "spring")
public interface NoteMapper {

    @Mapping(target = "id", expression = "java(setId(noteDtoRQ.getId()))")
    @Mapping(target = "dateTime", expression = "java(setDateTime(noteDtoRQ.getDateTime()))")
    NoteEntity mapNote(NoteDtoRQ noteDtoRQ);

    List<NoteDtoRS> mapListDto(List<NoteEntity> noteEntities);

    default String setId(String id) {
        if (isNull(id)) {
           return ObjectIdUtils.createId();
        } else {
            return id;
        }
    }

    default LocalDateTime setDateTime(LocalDateTime dateTime) {
        if (isNull(dateTime)) {
            return LocalDateTime.now();
        } else {
            return dateTime;
        }
    }

}
