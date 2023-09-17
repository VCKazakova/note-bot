package ru.vckazakova.notebot.service;

import ru.vckazakova.notebot.dto.TagDtoRQ;
import ru.vckazakova.notebot.dto.TagDtoRS;

import java.util.List;

public interface TagService {

    String createTag(TagDtoRQ tagDtoRQ);

    List<TagDtoRS> findAllTags();

    String updateTagByName(String oldTagName, String newTagName);

    String deleteTagByName(String tagName);

}
