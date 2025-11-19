package org.example.tblog.service;

import org.example.tblog.model.Comment;
import org.example.tblog.model.CommentsStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface CommentService {

    Comment save(Comment comment);
    Optional<Comment> findById(Integer id);
    void deleteById(Integer id);
    Page<Comment> findByPostId(Integer postId, Pageable pageable);
    Long countByPostId(Integer postId);
    Page<Comment> findByStatus(CommentsStatus status , Pageable pageable);
    Page<Comment> findByPostIdAndStatus(Integer postId, CommentsStatus status , Pageable pageable);
    Comment updateStatus(Integer commentId, CommentsStatus newStatus);
}