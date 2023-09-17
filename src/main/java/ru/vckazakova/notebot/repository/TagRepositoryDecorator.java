package ru.vckazakova.notebot.repository;

import ru.vckazakova.notebot.model.Tag;

import java.util.List;

public interface TagRepositoryDecorator {

    void saveTag(Tag tag);

    void updateTagName(String oldTagName, String newTagName);

    void updateTagName2(String oldTagName, String newTagName);

    List<Tag> findAll();

    void deleteTagByName(String tagName);

}
