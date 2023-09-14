package ru.vckazakova.notebot.service;

import ru.vckazakova.notebot.dto.TagDto;

import java.util.List;

public interface TagService {

    String createTag(TagDto tagDto);

    List<TagDto> findAllTags();

    String updateTagByName(String oldTagName, String newTagName);

    String deleteTagByName(String tagName);

}
