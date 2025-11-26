package org.example.tblog.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TagDto {
    public static class Create {
        @NotBlank(message = "tag.name.null")
        @Size(min = 2, max = 30, message = "tag.name.size")
        private String name;

        @NotBlank(message = "tag.slug.null")
        @Size(min = 2, max = 40, message = "tag.slug.size")
        private String slug;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }
    }
}
