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
public class QuestionRequest {

    @Size(min = 10, max = 1000, message = "Min question size is {min}, max is {max}")
    @NotBlank
    private String text;

    @NotBlank
    @Uuid
    private String accountId;

    private Boolean isAnonymous;
}
