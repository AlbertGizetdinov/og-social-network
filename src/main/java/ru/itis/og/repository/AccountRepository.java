package ru.itis.og.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.og.model.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {

    Optional<Account> findAccountByConfirmCode(String confirmCode);

}
