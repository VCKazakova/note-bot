package ru.vckazakova.notebot.utils;

import org.bson.types.ObjectId;

public class ObjectIdUtils {

    public static String createId() {
        ObjectId id = new ObjectId();
        return id.toString();
    }
}
