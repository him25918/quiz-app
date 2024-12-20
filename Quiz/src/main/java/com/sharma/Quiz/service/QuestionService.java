package com.sharma.Quiz.service;

import com.sharma.Quiz.entity.Question;
import com.sharma.Quiz.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionRepo questionRepo;

    public Question saveQuestion(Question question){
        return questionRepo.save(question);
    }

    public List<Question> getAllQuestions(){
        return questionRepo.findAll();
    }

    public List<Question> getAllQuestionsByCategory(String category){
        return questionRepo.findAllByCategory(category);
    }
}
