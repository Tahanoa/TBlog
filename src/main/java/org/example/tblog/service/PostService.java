package org.example.tblog.service;

import org.example.tblog.model.Post;
import org.example.tblog.model.Tag;
import org.example.tblog.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Page<Post> findAll(Pageable pageable);

    List<Post> findAll(User user);

    Optional<Post> findById(int id);

    Optional<Post> findBySlug(String slug);

    Post save(Post post);

    void deleteById(int id);

    List<Post> findByTag(Tag tag);

    long count();
}
