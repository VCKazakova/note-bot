package ru.vckazakova.notebot.note.service;

import ru.vckazakova.notebot.note.dto.NoteDtoRQ;
import ru.vckazakova.notebot.note.dto.NoteDtoRS;
import ru.vckazakova.notebot.note.dto.PeriodRQ;

import java.time.LocalDateTime;
import java.util.List;

public interface NoteService {

    String createNote(NoteDtoRQ noteDtoRQ);

    List<NoteDtoRS> findAllNotesByParameters(LocalDateTime fromDate, LocalDateTime toDate, String tag, int page, int size);

    List<NoteDtoRS> findAllNotesByParameters(PeriodRQ period, String tag, int page, int size);

}
