package ru.itis.og.dto.response;

import lombok.*;
import ru.itis.og.model.AbstractEntity;
import ru.itis.og.model.Question;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponse {

    private UUID id;
    private String text;
    private List<UUID> answerIdList;
    private UUID accountId;
    private Instant createdAt;
    private Boolean isAnonymous;
    private Boolean isAnswered;

    public static QuestionResponse from(Question question) {
        return QuestionResponse.builder()
                .id(question.getId())
                .text(question.getText())
                .answerIdList(question.getAnswers().stream().map(AbstractEntity::getId).collect(Collectors.toList()))
                .accountId(question.getAccountId())
                .createdAt(question.getCreatedAt())
                .isAnonymous(question.getIsAnonymous())
                .isAnswered(question.getIsAnswered())
                .build();
    }

    public static List<QuestionResponse> from(List<Question> questions) {
        return questions.stream().map(QuestionResponse::from).collect(Collectors.toList());
    }

}
