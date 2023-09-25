package ru.vckazakova.notebot.repositoryDecorator.note;

import org.springframework.data.mongodb.core.query.Query;

import java.util.Map;

public interface QueryBuilder {

    Query createQuery(Map<String, Object> parameters, int page, int size);

}
