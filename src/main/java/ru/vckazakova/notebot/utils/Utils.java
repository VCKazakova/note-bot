package ru.vckazakova.notebot.utils;

import org.bson.types.ObjectId;

public class Utils {

    public static String createTagId() {
        ObjectId id = new ObjectId();
        return id.toString();
    }
}
