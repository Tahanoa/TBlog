package org.example.tblog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.example.tblog.model.CommentsStatus;

import java.time.LocalDateTime;
public class CommentDto {

    public static class Create {
        @NotBlank(message = "comment.content.null")
        @Size(min = 1, max = 500, message = "comment.content.size")
        private String content;

        private int postId;
        private int userId;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getPostId() {
            return postId;
        }

        public void setPostId(int postId) {
            this.postId = postId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
