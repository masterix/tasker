package com.ps.tags.control;

import com.ps.exceptions.NotFoundException;
import com.ps.tags.boundary.TagsCrudRepository;
import com.ps.tags.entity.Tag;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TagsService {
    private final TagsCrudRepository tagsRepository;

    public TagsService(TagsCrudRepository tagsRepository) {
        this.tagsRepository = tagsRepository;
    }

    public Tag findById(Long id) {
        return tagsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tag not found for id: " + id));
    }

    public Set<Tag> findAllByIds(List<Long> tagIds) {
        return StreamSupport
                .stream(tagsRepository.findAllById(tagIds).spliterator(), false)
                .collect(Collectors.toSet());
    }
}
