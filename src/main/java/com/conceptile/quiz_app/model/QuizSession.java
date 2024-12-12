package com.conceptile.quiz_app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @ElementCollection
    private Map<Long, Boolean> questionAnswers; // QuestionId -> Correct/Incorrect
}
