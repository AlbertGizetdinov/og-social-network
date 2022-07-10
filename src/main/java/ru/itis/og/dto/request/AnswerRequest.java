package ru.itis.og.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.itis.og.validation.annotation.Uuid;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerRequest {

    @Size(min = 10, max = 1000, message = "Min answer size is {min}, max is {max}")
    private String answer;

    @NotBlank
    @Uuid
    private String answererId;

}
