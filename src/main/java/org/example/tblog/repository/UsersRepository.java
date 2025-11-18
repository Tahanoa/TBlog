package org.example.tblog.repository;

import org.example.tblog.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer> {
Optional<Users> findByUsername(String username);
}