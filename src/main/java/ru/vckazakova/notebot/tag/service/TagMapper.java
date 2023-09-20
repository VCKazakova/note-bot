package ru.vckazakova.notebot.tag.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.vckazakova.notebot.tag.dto.TagDtoRQ;
import ru.vckazakova.notebot.tag.dto.TagDtoRS;
import ru.vckazakova.notebot.repositoryDecorator.tagRepository.repository.TagEntity;

@Mapper(componentModel = "spring")
public interface TagMapper {

    @Mapping(target = "id", ignore = true)
    TagEntity mapTag(TagDtoRQ tagDtoRQ);

    TagDtoRS mapTagDto(TagEntity tagEntity);

}
