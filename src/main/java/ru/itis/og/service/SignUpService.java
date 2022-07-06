package ru.itis.og.service;

import ru.itis.og.dto.request.SignUpForm;
import ru.itis.og.dto.response.AccountDto;

public interface SignUpService {
    AccountDto signUp(SignUpForm signUpForm);

    void checkConfirm(String confirmCode);
}
