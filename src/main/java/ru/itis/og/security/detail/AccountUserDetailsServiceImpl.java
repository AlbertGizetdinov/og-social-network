package ru.itis.og.security.detail;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.itis.og.exception.AccountNotFoundException;
import ru.itis.og.repository.AccountRepository;

import java.util.function.Supplier;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountUserDetailsServiceImpl implements UserDetailsService {

    AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws AccountNotFoundException {
        return new AccountUserDetailsImpl(accountRepository.findByEmail(email)
                .orElseThrow((Supplier<RuntimeException>) ()
                        -> new AccountNotFoundException("Account with such id did not exists")
                )
        );
    }
}
