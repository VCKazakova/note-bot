package ru.vckazakova.notebot.repositoryDecorator;

import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MongoDBContainer;

@ActiveProfiles("test")
public abstract class IntegrationBased {

    public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2")
            .withReuse(true);

    static {
        mongoDBContainer.start();
        var mappedPort = mongoDBContainer.getMappedPort(27017);
        System.setProperty("mongodb.container.port", String.valueOf(mappedPort));
    }

}
