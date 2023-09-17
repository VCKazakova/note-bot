package ru.vckazakova.notebot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.vckazakova.notebot.dto.TagDtoRQ;
import ru.vckazakova.notebot.dto.TagDtoRS;
import ru.vckazakova.notebot.model.Tag;

@Mapper(componentModel = "spring")
public interface TagMapper {

    @Mapping(target = "id", ignore = true)
    Tag mapTag(TagDtoRQ tagDtoRQ);

    TagDtoRS mapTagDto(Tag tag);

}
