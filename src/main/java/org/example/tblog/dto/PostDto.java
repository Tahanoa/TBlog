package org.example.tblog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.example.tblog.model.Status;

import java.time.LocalDateTime;
import java.util.List;

public record PostDto(
        Integer id,
        String slug,
        @NotBlank @Size(max = 255)
        String title,
        @NotBlank String content,
        String excerpt,
        Status status,
        LocalDateTime publishedAt,
        int views,
        Integer categoryId,
        String categoryName,
        List<Integer> tagIds,
        List<String> tagNames
) {}
