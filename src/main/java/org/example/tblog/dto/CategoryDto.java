package org.example.tblog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryDto {
    public static class Create {
        @NotBlank(message = "category.name.null")
        @Size(min = 2, max = 50, message = "category.name.size")
        private String name;

        @Size(max = 200, message = "category.description.size")
        private String description;

        private String image;

        @NotBlank(message = "category.slug.null")
        @Size(min = 2, max = 60, message = "category.slug.size")
        private String slug;

        private Integer parentId;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public Integer getParentId() {
            return parentId;
        }

        public void setParentId(Integer parentId) {
            this.parentId = parentId;
        }
    }
}
