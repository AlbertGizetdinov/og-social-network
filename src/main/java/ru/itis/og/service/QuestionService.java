package ru.itis.og.service;

import ru.itis.og.dto.request.AnswerRequest;
import ru.itis.og.dto.request.QuestionRequest;
import ru.itis.og.dto.response.QuestionResponse;

import java.util.List;
import java.util.UUID;

public interface QuestionService {

    QuestionResponse askQuestion(QuestionRequest questionRequest);

    QuestionResponse answerQuestion(AnswerRequest answerRequest, UUID questionId);

    List<QuestionResponse> getAllAnsweredQuestions();

    List<QuestionResponse> getAllNotAnsweredQuestions();
}
