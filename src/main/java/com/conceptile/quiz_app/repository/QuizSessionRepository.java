package com.conceptile.quiz_app.repository;

import com.conceptile.quiz_app.model.QuizSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizSessionRepository extends JpaRepository<QuizSession, Long> {
}
