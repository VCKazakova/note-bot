package ru.vckazakova.notebot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.vckazakova.notebot.model.Tag;

public interface TagRepository extends MongoRepository<Tag, String> {
}
