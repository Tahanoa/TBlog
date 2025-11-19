package org.example.tblog.service;

import org.example.tblog.model.Comment;
import org.example.tblog.model.CommentsStatus;
import org.example.tblog.repository.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceIMPL implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceIMPL(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment save(Comment comment) {
        if (comment.getStatus() == null) {
            comment.setStatus(CommentsStatus.EXPECTANT);
        }
        return commentRepository.save(comment);
    }

    @Override
    public Optional<Comment> findById(Integer id) {
        return commentRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Page<Comment> findByPostId(Integer postId, Pageable pageable) {
        return commentRepository.findByPostIdOrderByTimeDesc(postId, pageable);
    }

    @Override
    public int countByPostId(Integer postId) {
        return commentRepository.countByPostId(postId);
    }

    @Override
    public Page<Comment> findByStatus(CommentsStatus status, Pageable pageable) {
        return commentRepository.findByStatus(status, pageable);
    }

    @Override
    public Page<Comment> findByPostIdAndStatus(Integer postId, CommentsStatus status, Pageable pageable) {
        return commentRepository.findByPostIdAndStatus(postId, status, pageable);
    }

    @Override
    public Comment updateStatus(Integer commentId, CommentsStatus newStatus) {
        return commentRepository.findById(commentId)
                .map(comment -> {
                    comment.setStatus(newStatus);
                    return commentRepository.save(comment);
                })
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + commentId));
    }
}