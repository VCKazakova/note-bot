package ru.vckazakova.notebot.mapper;

import org.mapstruct.Mapper;
import ru.vckazakova.notebot.dto.TagDto;
import ru.vckazakova.notebot.model.Tag;

@Mapper(componentModel = "spring")
public interface TagMapper {

    Tag mapTag(TagDto tagDto);

    TagDto mapTagDto(Tag tag);

}
