package ru.itis.og.security.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.og.exception.AccountNotFoundException;
import ru.itis.og.repository.AccountRepository;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserDetailsServiceImpl implements UserDetailsService {

    AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            return accountRepository.findByEmail(email).orElseThrow(AccountNotFoundException::new);
        } catch (AccountNotFoundException exception) {
            throw new UsernameNotFoundException(exception.getMessage(), exception);
        }
    }
}
