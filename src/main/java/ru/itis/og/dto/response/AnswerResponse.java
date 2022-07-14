package ru.itis.og.dto.response;

import lombok.*;
import ru.itis.og.model.Answer;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerResponse {

    private UUID id;
    private String text;
    private UUID accountId;
    private UUID questionId;
    private Instant createdAt;

    public static AnswerResponse from(Answer answer) {
        return AnswerResponse.builder()
                .id(answer.getId())
                .text(answer.getText())
                .accountId(answer.getAccountId())
                .questionId(answer.getQuestion().getId())
                .createdAt(answer.getCreatedAt())
                .build();
    }

    public static List<AnswerResponse> from(List<Answer> answers) {
        return answers.stream().map(AnswerResponse::from).collect(Collectors.toList());
    }

}
