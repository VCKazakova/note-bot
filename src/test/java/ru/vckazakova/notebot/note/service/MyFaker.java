package ru.vckazakova.notebot.note.service;

import com.github.javafaker.Faker;
import ru.vckazakova.notebot.note.dto.NoteDtoRS;
import ru.vckazakova.notebot.repositoryDecorator.noteRepository.repository.entity.NoteEntity;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyFaker extends Faker {

    public NoteEntity getRandomNote() {
        String id = super.bothify("##??#?");
        String tag = super.regexify("^#\\w{5}");
        String text = super.lorem().sentence();
        LocalDateTime dateTime =
                super.date().future(365, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return new NoteEntity(id, tag, text, dateTime);
    }

    public NoteDtoRS getRandomNoteDtoRS() {
        String tag = super.regexify("^#\\w{5}");
        String text = super.lorem().sentence();
        String  dateTime =
                super.date().future(365, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().toString();
        return NoteDtoRS.builder()
                .tag(tag)
                .text(text)
                .dateTime(dateTime)
                .build();
    }

    public List<NoteEntity> getRandomListNote(int size) {
        List<NoteEntity> noteEntities = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            noteEntities.add(getRandomNote());
        }
        return noteEntities;
    }

    public List<NoteDtoRS> getRandomListNoteDtoRS(int size) {
        List<NoteDtoRS> notes = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            notes.add(getRandomNoteDtoRS());
        }
        return notes;
    }



}
