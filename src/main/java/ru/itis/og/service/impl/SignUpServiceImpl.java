package ru.itis.og.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.og.dto.request.SignUpRequest;
import ru.itis.og.dto.response.AccountResponse;
import ru.itis.og.exception.AccountNotFoundException;
import ru.itis.og.model.Account;
import ru.itis.og.repository.AccountRepository;
import ru.itis.og.service.SignUpService;
import ru.itis.og.util.EmailUtil;

import java.time.Instant;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class SignUpServiceImpl implements SignUpService {
    private final AccountRepository accountRepository;
    private final EmailUtil emailUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public AccountResponse signUp(SignUpRequest signUpRequest) {
        Account account = Account.builder()
                .firstname(signUpRequest.getFirstname())
                .lastname(signUpRequest.getLastname())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .gender(signUpRequest.getGender())
                .birthday(Instant.parse(signUpRequest.getBirthday()))
                .confirmCode(UUID.randomUUID().toString())
                .state(Account.State.NOT_CONFIRMED)
                .build();

        emailUtil.sendConfirmMail(
                account.getEmail(), "Для завершения регистрации нажмите кнопку в письме",
                "confirmMail.ftl", account
        );

        return AccountResponse.from(accountRepository.save(account));
    }

    @Override
    public boolean checkConfirm(String confirmCode) {
        Account account = accountRepository.findAccountByConfirmCode(confirmCode)
                .orElseThrow(AccountNotFoundException::new);
        if (account.getState().equals(Account.State.NOT_CONFIRMED)) {
            account.setState(Account.State.CONFIRMED);
            account.setConfirmCode(null);
            accountRepository.save(account);
            return true;
        }
        return false;
    }
}
