package ru.itis.og.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.og.model.Gender;
import ru.itis.og.validation.annotation.ValidBirthday;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpForm {
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

    @ValidBirthday(message = "date is invalid")
    private String birthday;

    @NotBlank
    private Gender gender;
}

