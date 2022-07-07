package ru.itis.og.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.og.model.Account;

<<<<<<< src/main/java/ru/itis/og/repository/AccountRepository.java
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {

    Optional<Account> findAccountByConfirmCode(String confirmCode);

=======
>>>>>>> src/main/java/ru/itis/og/repository/AccountRepository.java
}
