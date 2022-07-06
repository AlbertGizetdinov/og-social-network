package ru.itis.og.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.og.dto.request.SignUpForm;
import ru.itis.og.dto.response.AccountDto;
import ru.itis.og.model.Account;
import ru.itis.og.model.State;
import ru.itis.og.repository.AccountRepository;
import ru.itis.og.service.AccountService;

import java.time.Instant;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
}
