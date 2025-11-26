package org.example.tblog.repository;

import org.example.tblog.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, Integer> {
    Page<Post> findByAuthorId(Integer authorId , Pageable pageable);

    Page<Post> findByTitleContainingIgnoreCase(String title , Pageable pageable);

    Page<Post> findByContentContainingIgnoreCase(String content , Pageable pageable);

    Page<Post> findByCategoryId(Integer categoryId, Pageable pageable);


}