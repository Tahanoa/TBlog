package org.example.tblog.dto;


import org.example.tblog.model.Role;

public record UserDto(int id, String username, Role role) {
}