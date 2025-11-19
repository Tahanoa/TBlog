package org.example.tblog.service;

import org.example.tblog.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface CategoryService {

    Category save(Category category);
    Optional<Category> findById(Integer id);
    Page<Category> findAll(Pageable pageable);
    void deleteById(Integer id);
    Page<Category> findByNameContaining(String name, Pageable pageable);
}