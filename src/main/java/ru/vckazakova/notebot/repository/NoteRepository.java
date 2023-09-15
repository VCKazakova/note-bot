package ru.vckazakova.notebot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ru.vckazakova.notebot.model.Note;

import java.time.LocalDateTime;
import java.util.List;

public interface NoteRepository extends MongoRepository<Note, String> {

    List<Note> findAllByTag(String tag);

    @Query("{$expr: {$eq: [{$year: '$dateTime'}, ?0]}}")
    List<Note> findAllByYear(int year);

    @Query("{$and: [{$expr: {$eq: [{$year: '$dateTime'}, ?0]}}, {'tag': ?1}]}")
    List<Note> findAllByYearAndTag(int year, String tag);

    List<Note> findAllByDateTimeBetween(LocalDateTime fromDate, LocalDateTime toDate);

    List<Note> findAllByDateTimeBetweenAndTag(LocalDateTime fromDate, LocalDateTime toDate, String tag);

}
