package org.example.tblog.controller;

import org.example.tblog.model.Comment;
import org.example.tblog.model.CommentsStatus;
import org.example.tblog.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<Comment> createComment(@Valid @RequestBody Comment comment) {
        Comment savedComment = commentService.save(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getComment(@PathVariable Integer id) {
        Optional<Comment> comment = commentService.findById(id);
        return comment.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/count/post/{postId}")
    public ResponseEntity<Integer> getCommentCountByPost(@PathVariable int postId) {
        int count = commentService.countByPostId(postId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<Page<Comment>> getCommentsByStatus(
            @PathVariable CommentsStatus status,
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) @Max(100) int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Comment> comments = commentService.findByStatus(status, pageable);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/post/{postId}/status/{status}")
    public ResponseEntity<Page<Comment>> getCommentsByPostAndStatus(
            @PathVariable Integer postId,
            @PathVariable CommentsStatus status,
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) @Max(100) int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Comment> comments = commentService.findByPostIdAndStatus(postId, status, pageable);
        return ResponseEntity.ok(comments);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Comment> updateCommentStatus(
            @PathVariable Integer id,
            @RequestParam CommentsStatus newStatus) {

        try {
            Comment updatedComment = commentService.updateStatus(id, newStatus);
            return ResponseEntity.ok(updatedComment);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer id) {
        if (commentService.findById(id).isPresent()) {
            commentService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}