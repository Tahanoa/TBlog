package org.example.tblog.repository;

import org.example.tblog.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    Tag findBySlug(String slug);
}
