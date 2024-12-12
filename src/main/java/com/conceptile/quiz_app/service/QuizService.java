package com.conceptile.quiz_app.service;

import com.conceptile.quiz_app.dto.*;
import com.conceptile.quiz_app.model.Question;
import com.conceptile.quiz_app.model.QuizSession;
import com.conceptile.quiz_app.repository.QuestionRepository;
import com.conceptile.quiz_app.repository.QuizSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class QuizService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizSessionRepository sessionRepository;

    public QuizDto.QuizSessionDto startQuiz(Long userId) {
        QuizSession session = new QuizSession();
        session.setUserId(userId);
        session.setQuestionAnswers(new HashMap<>());
        QuizSession savedSession = sessionRepository.save(session);
        return new QuizDto.QuizSessionDto(savedSession.getId());
    }

    public QuizDto.QuestionDto getRandomQuestion(Long sessionId) {
        QuizSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        List<Question> questions = questionRepository.findAll();
        Set<Long> answeredQuestions = session.getQuestionAnswers().keySet();

        List<Question> unansweredQuestions = questions.stream()
                .filter(q -> !answeredQuestions.contains(q.getId()))
                .toList();

        if (unansweredQuestions.isEmpty()) {
            throw new RuntimeException("No more questions available");
        }

        Question randomQuestion = unansweredQuestions.get(new Random().nextInt(unansweredQuestions.size()));
        return new QuizDto.QuestionDto(randomQuestion.getId(), randomQuestion.getText(), randomQuestion.getOptions());
    }

    public QuizDto.AnswerResponseDto submitAnswer(QuizDto.AnswerRequest request) {
        QuizSession session = sessionRepository.findById(request.getSessionId())
                .orElseThrow(() -> new RuntimeException("Session not found"));

        Question question = questionRepository.findById(request.getQuestionId())
                .orElseThrow(() -> new RuntimeException("Question not found"));

        boolean isCorrect = question.getCorrectAnswer().equals(request.getSelectedAnswer());
        session.getQuestionAnswers().put(request.getQuestionId(), isCorrect);
        sessionRepository.save(session);

        return new QuizDto.AnswerResponseDto(isCorrect, question.getCorrectAnswer());
    }

    public QuizDto.QuizSummaryDto getQuizSummary(Long sessionId) {
        QuizSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        int correctAnswers = (int) session.getQuestionAnswers().values().stream().filter(Boolean::booleanValue).count();
        int totalAnswered = session.getQuestionAnswers().size();
        int incorrectAnswers = totalAnswered - correctAnswers;

        return new QuizDto.QuizSummaryDto(totalAnswered, correctAnswers, incorrectAnswers);
    }
}

