package ru.vckazakova.notebot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.vckazakova.notebot.model.Tag;

@Repository
public interface TagRepository extends MongoRepository<Tag, String> {

    void deleteTagByName(String name);
}
