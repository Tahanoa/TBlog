package org.example.tblog.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

    public class UserDto {
        public static class Create {
            @Size(min = 3, max = 20, message = "username.size")
            @NotBlank(message = "username.null")
            private String username;

            @Size(min = 6, message = "password.size")
            @NotBlank(message = "password.null")
            private String password;

            @Email(message = "email.invalid")
            @NotBlank(message = "email.null")
            private String email;

            @Size(min = 2, max = 50, message = "fullname.size")
            @NotBlank(message = "fullname.null")
            private String fullName;


            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getFullName() {
                return fullName;
            }

            public void setFullName(String fullName) {
                this.fullName = fullName;
            }

        }
    }

