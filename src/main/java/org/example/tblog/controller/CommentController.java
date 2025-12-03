package org.example.tblog.controller;

import jakarta.validation.Valid;
import org.example.tblog.dto.CommentDto;
import org.example.tblog.model.Comment;
import org.example.tblog.model.Post;
import org.example.tblog.service.CommentService;
import org.example.tblog.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;
    private final PostService postService;

    public CommentController(CommentService commentService, PostService postService) {
        this.commentService = commentService;
        this.postService = postService;
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentDto>> getByPostId(@PathVariable Integer postId) {
        List<Comment> comments = commentService.findByPostId(postId);
        List<CommentDto> dtos = comments.stream()
                .map(c -> new CommentDto(
                        c.getId(),
                        c.getContent(),
                        c.getAuthorName(),
                        c.getAuthorEmail(),
                        c.getApproved(),
                        c.getPost() != null ? c.getPost().getId() : null,
                        c.getCreatedAt()
                ))
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable int id) {
        Optional<Comment> comment = commentService.findById(id);
        return comment.map(c -> ResponseEntity.ok(
                new CommentDto(
                        c.getId(),
                        c.getContent(),
                        c.getAuthorName(),
                        c.getAuthorEmail(),
                        c.getApproved(),
                        c.getPost() != null ? c.getPost().getId() : null,
                        c.getCreatedAt()
                )
        )).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> saveComment(@Valid @RequestBody CommentDto commentDto) {
        try {
            System.out.println("دریافت CommentDto: " + commentDto);
            System.out.println("postId: " + commentDto.getPostId());
            System.out.println("authorName: " + commentDto.getAuthorName());
            System.out.println("authorEmail: " + commentDto.getAuthorEmail());

            // اعتبارسنجی دستی برای اطمینان
            if (commentDto.getPostId() == null || commentDto.getPostId() <= 0) {
                return ResponseEntity.badRequest().body("Post ID is required and must be positive");
            }

            Comment comment = new Comment();
            comment.setContent(commentDto.getContent());
            comment.setAuthorName(commentDto.getAuthorName());
            comment.setAuthorEmail(commentDto.getAuthorEmail());
            comment.setApproved(0); // پیش‌فرض تایید نشده

            // یافتن پست
            Post post = postService.findById(commentDto.getPostId())
                    .orElseThrow(() -> new IllegalArgumentException("Post not found with id " + commentDto.getPostId()));
            comment.setPost(post);

            Comment savedComment = commentService.save(comment);

            // بروزرسانی DTO با اطلاعات ذخیره شده
            commentDto.setId(savedComment.getId());
            commentDto.setApproved(savedComment.getApproved());
            commentDto.setCreatedAt(savedComment.getCreatedAt());

            return ResponseEntity.ok(commentDto);

        } catch (Exception e) {
            System.err.println("خطا در ذخیره کامنت: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().body("خطا در ذخیره کامنت: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable int id) {
        commentService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<Page<CommentDto>> getAllComments(Pageable pageable) {
        Page<Comment> comments = commentService.findAll(pageable);
        Page<CommentDto> dtos = comments.map(c -> new CommentDto(
                c.getId(),
                c.getContent(),
                c.getAuthorName(),
                c.getAuthorEmail(),
                c.getApproved(),
                c.getPost() != null ? c.getPost().getId() : null,
                c.getCreatedAt()
        ));
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<?> approveComment(@PathVariable int id) {
        Optional<Comment> optionalComment = commentService.findById(id);
        if (optionalComment.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Comment comment = optionalComment.get();
        comment.setApproved(1);
        Comment updated = commentService.save(comment);

        CommentDto dto = new CommentDto(
                updated.getId(),
                updated.getContent(),
                updated.getAuthorName(),
                updated.getAuthorEmail(),
                updated.getApproved(),
                updated.getPost() != null ? updated.getPost().getId() : null,
                updated.getCreatedAt()
        );

        return ResponseEntity.ok(dto);
    }

    // حذف متد convertToEntity چون در saveComment استفاده نمی‌شود
}