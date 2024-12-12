package com.conceptile.quiz_app.controller;

import com.conceptile.quiz_app.dto.QuizDto;
import com.conceptile.quiz_app.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/start")
    public ResponseEntity<QuizDto.QuizSessionDto> startQuiz(@RequestBody QuizDto.StartQuizRequest request) {
        return ResponseEntity.ok(quizService.startQuiz(request.getUserId()));
    }

    @GetMapping("/question")
    public ResponseEntity<QuizDto.QuestionDto> getRandomQuestion(@RequestParam Long sessionId) {
        return ResponseEntity.ok(quizService.getRandomQuestion(sessionId));
    }

    @PostMapping("/answer")
    public ResponseEntity<QuizDto.AnswerResponseDto> submitAnswer(@RequestBody QuizDto.AnswerRequest request) {
        return ResponseEntity.ok(quizService.submitAnswer(request));
    }

    @GetMapping("/summary")
    public ResponseEntity<QuizDto.QuizSummaryDto> getQuizSummary(@RequestParam Long sessionId) {
        return ResponseEntity.ok(quizService.getQuizSummary(sessionId));
    }

    @GetMapping("/foo")
    public String foo(){
        return "Hi foo is calling";
    }
}

