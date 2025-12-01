package org.example.tblog.service;

import org.example.tblog.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    Page<User> findAll(Pageable pageable);
    User save(User user);
    Optional<User> findByUsername(String name);
    Optional<User> findById(int id);
}
