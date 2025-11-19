package org.example.tblog.service;

import org.example.tblog.model.Tag;
import java.util.List;
import java.util.Optional;

public interface TagService {

    Tag save(Tag tag);
    void deleteById(Integer id);
    List<Tag> findByNameContaining(String name);
    List<Tag> findByPostId(Integer postId);
}