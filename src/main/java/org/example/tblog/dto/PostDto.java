package org.example.tblog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.example.tblog.dto.UserDto;
import java.util.List;

public class PostDto {
    public static class Create {
        @Size(min = 3, max = 100, message = "title.size")
        @NotBlank(message = "title.null")
        private String title;

        @Size(min = 10, max = 5000, message = "content.size")
        @NotBlank(message = "content.null")
        private String content;

        private int categoryId;
        private List<Integer> tagIds;
        private int authorId;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public List<Integer> getTagIds() {
            return tagIds;
        }

        public void setTagIds(List<Integer> tagIds) {
            this.tagIds = tagIds;
        }

        public int getAuthorId() {
            return authorId;
        }

        public void setAuthorId(int authorId) {
            this.authorId = authorId;
        }
    }
}