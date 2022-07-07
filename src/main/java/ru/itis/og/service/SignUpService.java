package ru.itis.og.service;

import ru.itis.og.dto.request.SignUpRequest;
import ru.itis.og.dto.response.AccountResponse;

public interface SignUpService {
    AccountResponse signUp(SignUpRequest signUpRequest);

    boolean checkConfirm(String confirmCode);
}
