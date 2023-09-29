package ru.vckazakova.notebot.tag.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vckazakova.notebot.tag.dto.TagDtoRQ;
import ru.vckazakova.notebot.tag.dto.TagDtoRS;
import ru.vckazakova.notebot.repositoryDecorator.tag.repository.TagEntity;
import ru.vckazakova.notebot.repositoryDecorator.tag.TagRepositoryDecorator;
import ru.vckazakova.notebot.utils.ObjectIdUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepositoryDecorator tagRepository;
    private final TagMapper tagMapper;

    @Override
    public String createTag(TagDtoRQ tagDtoRQ) {
        String tagDtoName = tagDtoRQ.getName();
        log.info("Создание тэга = {} ", tagDtoName);
        TagEntity tagEntity = tagMapper.mapTag(tagDtoRQ);
        TagEntity saveTag = tagRepository.saveTag(tagEntity);
        return saveTag + " тэг успешно создан";
    }

    @Override
    public List<TagDtoRS> findAllTags(int page, int size) {
        log.info("Поиск всех тэгов");
        List<TagEntity> all = tagRepository.findAll(page, size);
        return all.stream()
                .map(tagMapper::mapTagDto)
                .collect(Collectors.toList());
    }

    @Override
    public String updateTagByName(String oldTagName, String newTagName) {
        log.info("Обновление тэга = {} ", oldTagName);
        tagRepository.updateTagName(oldTagName, newTagName);
        return "Тэг успешно обновлен, новое имя " + newTagName;
    }

    @Override
    public String deleteTagByName(String tagName) {
        log.info("Удаление тега = {} ", tagName);
        tagRepository.deleteTagByName(tagName);
        return tagName + " тэг успешно удален";
    }

    @Override
    public Optional<TagDtoRS> findTagByName(String tagName) {
        log.info("Поиск тэга={}", tagName);
        Optional<TagEntity> tagByName = tagRepository.findTagByName(tagName);
        return tagByName.map(tagMapper::mapTagDto);
    }
}
