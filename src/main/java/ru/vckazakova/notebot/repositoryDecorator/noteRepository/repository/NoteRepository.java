package ru.vckazakova.notebot.repositoryDecorator.noteRepository.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ru.vckazakova.notebot.repositoryDecorator.noteRepository.repository.entity.NoteEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface NoteRepository extends MongoRepository<NoteEntity, String> {

    List<NoteEntity> findAllByTag(String tag);

    @Query("{$expr: {$eq: [{$year: '$dateTime'}, ?0]}}")
    List<NoteEntity> findAllByYear(int year);

    @Query("{$and: [{$expr: {$eq: [{$year: '$dateTime'}, ?0]}}, {'tag': ?1}]}")
    List<NoteEntity> findAllByYearAndTag(int year, String tag);

    List<NoteEntity> findAllByDateTimeBetween(LocalDateTime fromDate, LocalDateTime toDate);

    List<NoteEntity> findAllByDateTimeBetweenAndTag(LocalDateTime fromDate, LocalDateTime toDate, String tag);

}
