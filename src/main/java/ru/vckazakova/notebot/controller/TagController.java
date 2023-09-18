package ru.vckazakova.notebot.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.vckazakova.notebot.dto.TagDtoRQ;
import ru.vckazakova.notebot.dto.TagDtoRS;
import ru.vckazakova.notebot.service.TagService;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @PostMapping("/tag")
    public ResponseEntity<String> createTag(@RequestBody @Valid TagDtoRQ tagDtoRQ) {
        String tag = tagService.createTag(tagDtoRQ);
        return ResponseEntity.status(201)
                .body(tag);
    }

    @GetMapping("/tag")
    public ResponseEntity<List<TagDtoRS>> findAllTags() {
        List<TagDtoRS> allTags = tagService.findAllTags();
        return ResponseEntity.ok(allTags);
    }

    @PatchMapping("/tag/{oldTagName}")
    public ResponseEntity<String> updateTagByName(
            @PathVariable String oldTagName,
            @RequestParam @NotBlank String newTagName) {
        String updateTagByName = tagService.updateTagByName(oldTagName, newTagName);
        return ResponseEntity.status(201)
                .body(updateTagByName);
    }

    @DeleteMapping("/tag/{tagName}")
    public ResponseEntity<String> deleteTagByName(@PathVariable String tagName) {
        String deleteTagByName = tagService.deleteTagByName(tagName);
        return ResponseEntity.ok(deleteTagByName);
    }

}
