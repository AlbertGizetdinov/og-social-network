package ru.itis.og.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.og.dto.request.SignUpForm;
import ru.itis.og.dto.response.AccountDto;
import ru.itis.og.service.SignUpService;

@RequiredArgsConstructor
@RestController
public class SignUpController {
    private final SignUpService signUpService;

    public ResponseEntity<String> checkConfirmCode(String confirmCode) {
        signUpService.checkConfirm(confirmCode);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Success");
    }

    public ResponseEntity<AccountDto> signUp(SignUpForm signUpForm) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(signUpService.signUp(signUpForm));
    }
}

