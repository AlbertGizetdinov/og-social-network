package ru.itis.og.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.og.dto.request.AnswerRequest;
import ru.itis.og.dto.request.QuestionRequest;
import ru.itis.og.dto.response.QuestionResponse;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
import static ru.itis.og.constant.OgConstant.*;

@RequestMapping(QUESTION_CONTROLLER_PATH)
public interface QuestionApi {

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    ResponseEntity<QuestionResponse> askQuestion(@Valid @RequestBody QuestionRequest questionRequest);

    @PatchMapping(value = QUESTION_ANSWER_CONTROLLER_PATH, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<QuestionResponse> answerQuestion(@Valid @RequestBody AnswerRequest answerRequest,
                                                    @PathVariable("question-id")UUID questionId);

    @GetMapping(value = QUESTION_GET_ANSWERED_CONTROLLER_PATH)
    ResponseEntity<List<QuestionResponse>> getAllAnsweredQuestions();

    @GetMapping(value = QUESTION_GET_NOT_ANSWERED_CONTROLLER_PATH)
    ResponseEntity<List<QuestionResponse>> getAllNotAnsweredQuestions();
}
