package ru.vckazakova.notebot.tag;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.vckazakova.notebot.tag.dto.TagDtoRQ;
import ru.vckazakova.notebot.tag.dto.TagDtoRS;
import ru.vckazakova.notebot.tag.service.TagService;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class TagControllerImpl implements TagController {

    private final TagService tagService;

    public String createTag(TagDtoRQ tagDtoRQ) {
        return tagService.createTag(tagDtoRQ);
    }

    public List<TagDtoRS> findAllTags(int page, int size) {
        return tagService.findAllTags(page, size);
    }

    public String updateTagByName(String tagName, String newTagName) {
        return tagService.updateTagByName(tagName, newTagName);
    }

    public String deleteTagByName(String tagName) {
        return tagService.deleteTagByName(tagName);
    }

}
