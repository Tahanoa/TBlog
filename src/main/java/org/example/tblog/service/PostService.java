package org.example.tblog.service;

import org.example.tblog.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface PostService {
    Post save(Post post);
    Optional<Post> findById(Integer id);
    Page<Post> findAll(Pageable pageable);
    void deleteById(Integer id);
    Post update(Integer id, Post post);
    Page<Post> findByTitleAndContentContaining(String title, Pageable pageable);
    Page<Post> findByCategoryId(Integer categoryId, Pageable pageable);
}