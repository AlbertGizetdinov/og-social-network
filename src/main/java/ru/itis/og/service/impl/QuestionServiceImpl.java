package ru.itis.og.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.og.dto.request.AnswerRequest;
import ru.itis.og.dto.request.QuestionRequest;
import ru.itis.og.dto.response.AnswerResponse;
import ru.itis.og.dto.response.QuestionResponse;
import ru.itis.og.exception.QuestionNotFoundException;
import ru.itis.og.model.Answer;
import ru.itis.og.model.Question;
import ru.itis.og.repository.AnswerRepository;
import ru.itis.og.repository.QuestionRepository;
import ru.itis.og.service.QuestionService;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    private final AnswerRepository answerRepository;

    @Override
    public QuestionResponse askQuestion(QuestionRequest questionRequest) {
        Question newQuestion = Question.builder()
                .text(questionRequest.getText())
                .accountId(questionRequest.getIsAnonymous() ? null : UUID.fromString(questionRequest.getAccountId()))
                .createdAt(Instant.now())
                .answers(new ArrayList<Answer>())
                .isAnswered(false)
                .isAnonymous(questionRequest.getIsAnonymous())
                .build();

        return QuestionResponse.from(questionRepository.save(newQuestion));
    }

    @Override
    public AnswerResponse addAnswer(AnswerRequest answerRequest, UUID questionId) {
        Question question = questionRepository.findById(questionId).orElseThrow(QuestionNotFoundException::new);

        Answer newAnswer = Answer.builder()
                .text(answerRequest.getText())
                .accountId(UUID.fromString(answerRequest.getAccountId()))
                .createdAt(Instant.now())
                .question(question)
                .build();

        question.setIsAnswered(true);
        questionRepository.save(question);

        return AnswerResponse.from(answerRepository.save(newAnswer));
    }

    @Override
    public List<QuestionResponse> getAllAnsweredQuestions() {
        return QuestionResponse.from(questionRepository.findAllByIsAnsweredTrue());
    }

    @Override
    public List<QuestionResponse> getAllNotAnsweredQuestions() {
        return QuestionResponse.from(questionRepository.findAllByIsAnsweredFalse());
    }

    @Override
    public List<AnswerResponse> getAllQuestionAnswers(UUID questionId) {
        Question question = questionRepository.findById(questionId).orElseThrow(QuestionNotFoundException::new);

        return AnswerResponse.from(question.getAnswers());
    }
}
