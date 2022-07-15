package ru.itis.og.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.og.validation.annotation.Birthday;
import ru.itis.og.validation.annotation.ValidGender;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {
    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    @NotBlank
    private String nickname;

    @Pattern(message = "email should looks like email", regexp = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$")
    private String email;

    @NotBlank
    private String password;

    @Birthday(message = "date is invalid")
    private String birthday;

    @ValidGender
    private String gender;
}

