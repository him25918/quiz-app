package com.sharma.Quiz.controller;

import com.sharma.Quiz.entity.Question;
import com.sharma.Quiz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/questions")
    public List<Question> getQuestions(){
        return questionService.getAllQuestions();
    }

    @PostMapping("/save")
    public Question saveQuestion(@RequestBody Question question){
        return questionService.saveQuestion(question);
    }

    @GetMapping("/ques")
    public List<Question> getQuestionsByCategory(@RequestParam String category){
        return questionService.getAllQuestionsByCategory(category);
    }
}
