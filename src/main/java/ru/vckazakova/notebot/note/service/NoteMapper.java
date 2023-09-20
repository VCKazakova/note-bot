package ru.vckazakova.notebot.note.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.vckazakova.notebot.repositoryDecorator.noteRepository.repository.entity.NoteEntity;
import ru.vckazakova.notebot.note.dto.NoteDtoRQ;
import ru.vckazakova.notebot.note.dto.NoteDtoRS;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NoteMapper {

    @Mapping(target = "id", ignore = true)
    NoteEntity mapNote(NoteDtoRQ noteDtoRQ);

    List<NoteDtoRS> mapListDto(List<NoteEntity> noteEntities);

}
