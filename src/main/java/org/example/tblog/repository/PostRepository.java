package org.example.tblog.repository;

import org.example.tblog.model.Post;
import org.example.tblog.model.Tag;
import org.example.tblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Integer> {
    Optional<Post> findBySlug(String slug);
    List<Post> findAllByAuthor(User author);
    List<Post> findAllByTagsContaining(Tag tag);
}