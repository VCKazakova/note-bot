package ru.vckazakova.notebot.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.vckazakova.notebot.tag.dto.TagDtoRQ;
import ru.vckazakova.notebot.tag.dto.TagDtoRS;
import ru.vckazakova.notebot.tag.service.TagService;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class TagHelperImpl implements TagHelper {

    private final TagService tagService;

    @Override
    public void saveTag(String tagName) {
        log.info("Проверяем, есть ли тэг={} в базе", tagName);
        Optional<TagDtoRS> tagByName = tagService.findTagByName(tagName);
        if (tagByName.isEmpty()) {
            tagService.createTag(TagDtoRQ.builder()
                    .name(tagName)
                    .build());
        }
    }
}
