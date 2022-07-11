package ru.itis.og.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.og.api.QuestionApi;
import ru.itis.og.dto.request.AnswerRequest;
import ru.itis.og.dto.request.QuestionRequest;
import ru.itis.og.dto.response.QuestionResponse;
import ru.itis.og.service.QuestionService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class QuestionController implements QuestionApi {

    private final QuestionService questionService;

    @Override
    public ResponseEntity<QuestionResponse> askQuestion(QuestionRequest questionRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(questionService.askQuestion(questionRequest));
    }

    @Override
    public ResponseEntity<QuestionResponse> answerQuestion(AnswerRequest answerRequest, UUID questionId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(questionService.answerQuestion(answerRequest, questionId));
    }

    @Override
    public ResponseEntity<List<QuestionResponse>> getAllAnsweredQuestions() {
        return ResponseEntity.ok(questionService.getAllAnsweredQuestions());
    }

    @Override
    public ResponseEntity<List<QuestionResponse>> getAllNotAnsweredQuestions() {
        return ResponseEntity.ok(questionService.getAllNotAnsweredQuestions());
    }
}
