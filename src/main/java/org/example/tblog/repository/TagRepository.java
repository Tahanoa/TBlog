package org.example.tblog.repository;

import org.example.tblog.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Integer> {

    List<Tag> findByNameContainingIgnoreCase(String name);
    List<Tag> findByPostId(Integer postId);
}