package ru.vckazakova.notebot.repositoryDecorator.tag.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends MongoRepository<TagEntity, String> {

    void deleteTagByName(String name);

    Optional<TagEntity> findTagEntityByName(String name);
}
