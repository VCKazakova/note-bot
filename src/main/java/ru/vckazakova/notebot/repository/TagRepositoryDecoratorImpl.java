package ru.vckazakova.notebot.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import ru.vckazakova.notebot.model.Tag;

@Component
@RequiredArgsConstructor
public class TagRepositoryDecoratorImpl implements TagRepositoryDecorator {

    private final MongoTemplate mongoTemplate;

    @Override
    public void updateTagName(String oldTagName, String newTagName) {
        Query query = new Query(Criteria.where("name").is(oldTagName));
        Update update = new Update().set("name", newTagName);
        mongoTemplate.updateFirst(query, update, Tag.class);
    }

    @Override
    // метод с использование java script
    public void updateTagName2(String oldTagName, String newTagName) {
        String jsQuery =
                "db.tag.update({ 'name': '" + oldTagName + "' }, { $set: { 'name': '" + newTagName + "' } }, { multi: true })";
        mongoTemplate.executeCommand(jsQuery);
    }
}
