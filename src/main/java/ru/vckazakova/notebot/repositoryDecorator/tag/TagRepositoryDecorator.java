package ru.vckazakova.notebot.repositoryDecorator.tag;

import ru.vckazakova.notebot.repositoryDecorator.tag.repository.TagEntity;

import java.util.List;

public interface TagRepositoryDecorator {

    TagEntity saveTag(TagEntity tagEntity);

    void updateTagName(String oldTagName, String newTagName);

    List<TagEntity> findAll(int page, int size);

    void deleteTagByName(String tagName);

}
