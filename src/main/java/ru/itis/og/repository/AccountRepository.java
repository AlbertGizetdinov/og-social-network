package ru.itis.og.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.og.model.Account;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {

    Optional<Account> findAccountByConfirmCode(String confirmCode);

    Optional<Account> findByEmail(String email);

}
