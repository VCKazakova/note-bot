package ru.vckazakova.notebot.tag.service;

import ru.vckazakova.notebot.tag.dto.TagDtoRQ;
import ru.vckazakova.notebot.tag.dto.TagDtoRS;

import java.util.List;
import java.util.Optional;

public interface TagService {

    String createTag(TagDtoRQ tagDtoRQ);

    List<TagDtoRS> findAllTags(int page, int size);

    String updateTagByName(String oldTagName, String newTagName);

    String deleteTagByName(String tagName);

    Optional<TagDtoRS> findTagByName(String tagName);
}
