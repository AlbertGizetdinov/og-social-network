package ru.itis.og.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.og.dto.request.SignUpForm;
import ru.itis.og.dto.response.AccountDto;
import ru.itis.og.exception.AccountAlreadyExistsException;
import ru.itis.og.exception.AccountNotFoundException;
import ru.itis.og.model.Account;
import ru.itis.og.model.State;
import ru.itis.og.repository.AccountRepository;
import ru.itis.og.service.AccountService;
import ru.itis.og.service.SignUpService;
import ru.itis.og.util.EmailUtil;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

@RequiredArgsConstructor
@Service
public class SignUpServiceImpl implements SignUpService {
    private final AccountRepository accountRepository;
    private final EmailUtil emailUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public AccountDto signUp(SignUpForm signUpForm) {
        Optional<Account> account = accountRepository.findAccountByEmail(signUpForm.getEmail());
        if (account.isPresent()) {
            throw new AccountAlreadyExistsException("Cannot register new account because account with this email is already registered");
        } else {
            Account newAccount = Account.builder()
                    .firstname(signUpForm.getFirstname())
                    .lastname(signUpForm.getLastname())
                    .nickname(signUpForm.getLastname())
                    .email(signUpForm.getEmail())
                    .password(passwordEncoder.encode(signUpForm.getPassword()))
                    .gender(signUpForm.getGender())
                    .birthday(Instant.parse(signUpForm.getBirthday()))
                    .confirmCode(UUID.randomUUID().toString())
                    .state(State.NOT_CONFIRMED)
                    .build();

            emailUtil.sendConfirmMail(newAccount.getEmail(), "Для завершения регистрации нажмите кнопку в письме", "confirmMail.ftl", newAccount);

            return AccountDto.from(accountRepository.save(newAccount));
        }
    }

    @Override
    public void checkConfirm(String confirmCode) {
        Account account = accountRepository.findAccountByConfirmCode(confirmCode).orElseThrow((Supplier<RuntimeException>) ()
                -> new AccountNotFoundException("Account not found")
        );
        if (account.getState().equals(State.NOT_CONFIRMED)) {
            account.setState(State.CONFIRMED);
            accountRepository.save(account);
        }
    }
}
