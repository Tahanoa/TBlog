package org.example.tblog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CommentDto {

        private Integer id;

        @NotBlank(message = "Content is required")
        private String content;

        @NotBlank(message = "Author name is required")
        @Size(max = 255, message = "Author name max length is 255")
        @JsonProperty("authorName")
        private String authorName;

        @Email(message = "Invalid email format")
        @NotBlank(message = "Author email is required")
        @JsonProperty("authorEmail")
        private String authorEmail;

        private Integer approved;

        @NotNull(message = "Post ID is required")
        @JsonProperty("postId")
        private Integer postId;

        private String createdAt;

        // Constructors
        public CommentDto() {}

        public CommentDto(Integer id, String content, String authorName,
                          String authorEmail, Integer approved, Integer postId,
                          String createdAt) {
                this.id = id;
                this.content = content;
                this.authorName = authorName;
                this.authorEmail = authorEmail;
                this.approved = approved;
                this.postId = postId;
                this.createdAt = createdAt;
        }

        // Getters and Setters
        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public String getContent() {
                return content;
        }

        public void setContent(String content) {
                this.content = content;
        }

        public String getAuthorName() {
                return authorName;
        }

        public void setAuthorName(String authorName) {
                this.authorName = authorName;
        }

        public String getAuthorEmail() {
                return authorEmail;
        }

        public void setAuthorEmail(String authorEmail) {
                this.authorEmail = authorEmail;
        }

        public Integer getApproved() {
                return approved;
        }

        public void setApproved(Integer approved) {
                this.approved = approved;
        }

        public Integer getPostId() {
                return postId;
        }

        public void setPostId(Integer postId) {
                this.postId = postId;
        }

        public String getCreatedAt() {
                return createdAt;
        }

        public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
        }

        // toString برای دیباگ
        @Override
        public String toString() {
                return "CommentDto{" +
                        "id=" + id +
                        ", content='" + content + '\'' +
                        ", authorName='" + authorName + '\'' +
                        ", authorEmail='" + authorEmail + '\'' +
                        ", approved=" + approved +
                        ", postId=" + postId +
                        ", createdAt='" + createdAt + '\'' +
                        '}';
        }
}