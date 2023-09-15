package ru.vckazakova.notebot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.vckazakova.notebot.dto.NoteDtoRQ;
import ru.vckazakova.notebot.dto.NoteDtoRS;
import ru.vckazakova.notebot.model.Note;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NoteMapper {

    @Mapping(target = "id", ignore = true)
    Note mapNote(NoteDtoRQ noteDtoRQ);

    List<NoteDtoRS> mapListDto(List<Note> notes);

}
