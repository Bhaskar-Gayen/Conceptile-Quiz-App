package com.conceptile.quiz_app.repository;

import com.conceptile.quiz_app.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
