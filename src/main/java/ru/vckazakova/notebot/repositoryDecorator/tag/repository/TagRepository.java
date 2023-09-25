package ru.vckazakova.notebot.repositoryDecorator.tag.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends MongoRepository<TagEntity, String> {

    void deleteTagByName(String name);
}
