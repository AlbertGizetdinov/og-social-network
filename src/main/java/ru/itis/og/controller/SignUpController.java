package ru.itis.og.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.og.api.SignUpApi;
import ru.itis.og.dto.request.SignUpRequest;
import ru.itis.og.dto.response.AccountResponse;
import ru.itis.og.service.SignUpService;

@RequiredArgsConstructor
@RestController
public class SignUpController implements SignUpApi {
    private final SignUpService signUpService;

    public ResponseEntity<String> checkConfirmCode(String confirmCode) {
        if (signUpService.checkConfirm(confirmCode))
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Successfully");
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unsuccessfully");
    }

    public ResponseEntity<AccountResponse> signUp(SignUpRequest signUpRequest) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(signUpService.signUp(signUpRequest));
    }
}

