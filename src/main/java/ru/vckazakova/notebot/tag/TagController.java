package ru.vckazakova.notebot.tag;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.vckazakova.notebot.tag.dto.TagDtoRQ;
import ru.vckazakova.notebot.tag.dto.TagDtoRS;

import java.util.List;

@Validated
@RequestMapping("/v1/tags")
public interface TagController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    String createTag(@RequestBody @Valid TagDtoRQ tagDtoRQ);

    @GetMapping
    List<TagDtoRS> findAllTags(@RequestParam(required = false, defaultValue = "0") int page,
                               @RequestParam(required = false, defaultValue = "5") int size);

    @PatchMapping("/{tagName}")
    String updateTagByName(@PathVariable String tagName,
                           @RequestParam @NotBlank String newTagName);

    @DeleteMapping("/{tagName}")
    String deleteTagByName(@PathVariable String tagName);
}
