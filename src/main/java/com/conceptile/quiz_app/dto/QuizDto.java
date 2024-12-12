package com.conceptile.quiz_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class QuizDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StartQuizRequest {
        private Long userId;

    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class QuizSessionDto {
        private Long sessionId;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class QuestionDto {
        private Long questionId;
        private String questionText;
        private List<String> options;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AnswerRequest {
        private Long sessionId;
        private Long questionId;
        private String selectedAnswer;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AnswerResponseDto {
        private boolean isCorrect;
        private String correctAnswer;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class QuizSummaryDto {
        private int totalAnswered;
        private int correctAnswers;
        private int incorrectAnswers;
    }

}
