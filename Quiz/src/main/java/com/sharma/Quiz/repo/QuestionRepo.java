package com.sharma.Quiz.repo;

import com.sharma.Quiz.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepo extends JpaRepository<Question, Long> {

    List<Question> findAllByCategory(String category);
}
