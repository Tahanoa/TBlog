package org.example.tblog.service;

import org.example.tblog.model.Tag;
import org.example.tblog.repository.TagRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TagServiceIMPL implements TagService {

    private final TagRepository tagRepository;
    public TagServiceIMPL(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag save(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public void deleteById(Integer id) {
        tagRepository.deleteById(id);
    }

    @Override
    public List<Tag> findByNameContaining(String name) {
        return tagRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Tag> findByPostId(Integer postId) {
        return tagRepository.findByPostId(postId);
    }
}