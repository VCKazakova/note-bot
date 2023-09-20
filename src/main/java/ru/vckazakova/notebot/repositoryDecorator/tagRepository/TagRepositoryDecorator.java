package ru.vckazakova.notebot.repositoryDecorator.tagRepository;

import ru.vckazakova.notebot.repositoryDecorator.tagRepository.repository.TagEntity;

import java.util.List;

public interface TagRepositoryDecorator {

    void saveTag(TagEntity tagEntity);

    void updateTagName(String oldTagName, String newTagName);

    List<TagEntity> findAll();

    void deleteTagByName(String tagName);

}
