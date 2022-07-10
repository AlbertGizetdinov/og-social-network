package ru.itis.og.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.og.dto.request.AnswerRequest;
import ru.itis.og.dto.request.QuestionRequest;
import ru.itis.og.dto.response.QuestionResponse;
import ru.itis.og.exception.QuestionNotFoundException;
import ru.itis.og.model.Question;
import ru.itis.og.repository.QuestionRepository;
import ru.itis.og.service.QuestionService;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Override
    public QuestionResponse askQuestion(QuestionRequest questionRequest) {
        Question newQuestion = Question.builder()
                .text(questionRequest.getText())
                .questioner(questionRequest.getIsAnonymous() ? null : UUID.fromString(questionRequest.getQuestionerId()))
                .createdAt(Instant.now())
                .isAnswered(false)
                .isAnonymous(questionRequest.getIsAnonymous())
                .build();

        return QuestionResponse.from(questionRepository.save(newQuestion));
    }

    @Override
    public QuestionResponse answerQuestion(AnswerRequest answerRequest, UUID questionId) {
        Question question = questionRepository.findById(questionId).orElseThrow(QuestionNotFoundException::new);
        if (!question.getIsAnswered()) {
            question.setAnswer(answerRequest.getAnswer());
            question.setAnswerer(UUID.fromString(answerRequest.getAnswererId()));
            question.setIsAnswered(true);
        }

        return QuestionResponse.from(questionRepository.save(question));
    }

    @Override
    public List<QuestionResponse> getAllAnsweredQuestions() {
        return QuestionResponse.from(questionRepository.findAllByIsAnsweredTrue());
    }

    @Override
    public List<QuestionResponse> getAllNotAnsweredQuestions() {
        return QuestionResponse.from(questionRepository.findAllByIsAnsweredFalse());
    }
}
