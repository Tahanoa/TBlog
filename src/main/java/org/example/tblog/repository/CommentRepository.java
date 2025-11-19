package org.example.tblog.repository;

import org.example.tblog.model.Comment;
import org.example.tblog.model.CommentsStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Page<Comment> findByPostIdOrderByTimeDesc(Integer postId , Pageable pageable);
    Page<Comment> findByPostIdAndStatus(Integer postId, CommentsStatus status, Pageable pageable);
    Long countByPostId(Integer postId);
}