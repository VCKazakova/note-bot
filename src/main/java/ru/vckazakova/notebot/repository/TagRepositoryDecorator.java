package ru.vckazakova.notebot.repository;

public interface TagRepositoryDecorator {

    void updateTagName(String oldTagName, String newTagName);

    void updateTagName2(String oldTagName, String newTagName);

}
