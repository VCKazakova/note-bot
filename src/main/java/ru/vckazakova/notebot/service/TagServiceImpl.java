package ru.vckazakova.notebot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vckazakova.notebot.dto.TagDto;
import ru.vckazakova.notebot.mapper.TagMapper;
import ru.vckazakova.notebot.model.Tag;
import ru.vckazakova.notebot.repository.TagRepository;
import ru.vckazakova.notebot.repository.TagRepositoryDecorator;

import java.util.List;
import java.util.stream.Collectors;

import static ru.vckazakova.notebot.utils.Utils.createTagId;

@Slf4j
@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final TagRepositoryDecorator tagRepositoryDecorator;
    private final TagMapper tagMapper;

    @Override
    @Transactional
    public String createTag(TagDto tagDto) {
        String tagDtoName = tagDto.getName();
        log.info("Создание тэга = {} ", tagDtoName);
        Tag tag = tagMapper.mapTag(tagDto);
        String tagId = createTagId();
        tag.setName(tagId);
        tagRepository.save(tag);
        return tagDtoName + " тэг успешно создан";
    }

    @Override
    @Transactional(readOnly = true)
    public List<TagDto> findAllTags() {
        log.info("Поиск всех тэгов");
        List<Tag> all = tagRepository.findAll();
        return all.stream()
                .map(tagMapper::mapTagDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public String updateTagByName(String oldTagName, String newTagName) {
        log.info("Обновление тэга = {} ", oldTagName);
        tagRepositoryDecorator.updateTagName(oldTagName, newTagName);
        return "Тэг успешно обновлен, новое имя " + newTagName;
    }

    @Override
    @Transactional
    public String deleteTagByName(String tagName) {
        log.info("Удаление тега = {} ", tagName);
        tagRepository.deleteTagByName(tagName);
        return tagName + " тэг успешно удален";
    }
}
