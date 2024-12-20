package com.sharma.Quiz.repo;

import com.sharma.Quiz.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepo extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
