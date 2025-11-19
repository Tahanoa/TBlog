package org.example.tblog.controller;

import org.example.tblog.model.Tag;
import org.example.tblog.service.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    private final TagService tagService;
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping
    public ResponseEntity<Tag> createTag(@Valid @RequestBody Tag tag) {
        Tag savedTag = tagService.save(tag);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTag);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Tag>> searchTags(@RequestParam @NotBlank String name) {
        List<Tag> tags = tagService.findByNameContaining(name);
        return ResponseEntity.ok(tags);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Tag>> getTagsByPost(@PathVariable Integer postId) {
        List<Tag> tags = tagService.findByPostId(postId);
        return ResponseEntity.ok(tags);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable Integer id) {
        tagService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}