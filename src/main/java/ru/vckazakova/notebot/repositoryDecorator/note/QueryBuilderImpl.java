package ru.vckazakova.notebot.repositoryDecorator.note;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class QueryBuilderImpl implements QueryBuilder {

    @Override
    public Query createQuery(Map<String, Object> parameters, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Criteria criteria = this.createCriteriaForQuery(parameters);
        return new Query(criteria).with(pageable);
    }

    private Criteria createCriteriaForQuery(Map<String, Object> parameters) {
        Criteria criteria = new Criteria();
        if (parameters.containsKey("fromDate") && parameters.containsKey("toDate")) {
            criteria.and("dateTime").gte(parameters.get("fromDate")).lte(parameters.get("toDate"));
        } else if (parameters.containsKey("toDate")) {
            criteria.and("dateTime").lte(parameters.get("toDate"));
        } else if (parameters.containsKey("fromDate")) {
            criteria.and("dateTime").gte(parameters.get("fromDate"));
        }
        if (parameters.containsKey("tag")) {
            criteria.and("tag").is(parameters.get("tag"));
        }
        return criteria;
    }
}
