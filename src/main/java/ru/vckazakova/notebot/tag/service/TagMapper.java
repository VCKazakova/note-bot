package ru.vckazakova.notebot.tag.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.vckazakova.notebot.tag.dto.TagDtoRQ;
import ru.vckazakova.notebot.tag.dto.TagDtoRS;
import ru.vckazakova.notebot.repositoryDecorator.tag.repository.TagEntity;
import ru.vckazakova.notebot.utils.ObjectIdUtils;

import static java.util.Objects.isNull;

@Mapper(componentModel = "spring")
public interface TagMapper {

    @Mapping(target = "id", expression = "java(setTag(tagDtoRQ.getId()))")
    TagEntity mapTag(TagDtoRQ tagDtoRQ);

    TagDtoRS mapTagDto(TagEntity tagEntity);

    default String setTag(String id) {
        if (isNull(id)) {
            return ObjectIdUtils.createId();
        } else {
            return id;
        }
    }
}
