package ru.vckazakova.notebot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vckazakova.notebot.dto.TagDto;
import ru.vckazakova.notebot.mapper.TagMapper;
import ru.vckazakova.notebot.model.Tag;
import ru.vckazakova.notebot.repository.TagRepositoryDecorator;
import ru.vckazakova.notebot.utils.ObjectIdUtils;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepositoryDecorator tagRepository;
    private final TagMapper tagMapper;

    @Override
    public String createTag(TagDto tagDto) {
        String tagDtoName = tagDto.getName();
        log.info("Создание тэга = {} ", tagDtoName);
        Tag tag = tagMapper.mapTag(tagDto);
        String tagId = ObjectIdUtils.createId();
        tag.setName(tagId);
        tagRepository.saveTag(tag);
        return tagDtoName + " тэг успешно создан";
    }

    @Override
    public List<TagDto> findAllTags() {
        log.info("Поиск всех тэгов");
        List<Tag> all = tagRepository.findAll();
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
}
