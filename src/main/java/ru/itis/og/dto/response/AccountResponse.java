package ru.itis.og.dto.response;

<<<<<<< src/main/java/ru/itis/og/dto/response/AccountResponse.java
import lombok.*;
import ru.itis.og.model.Account;
import ru.itis.og.model.Gender;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {
    private UUID id;
    private String firstname;
    private String lastname;
    private String nickname;
    private String email;
    private Instant birthday;
    private String gender;

    public static AccountResponse from(Account account) {
        return AccountResponse.builder()
                .id(account.getId())
                .firstname(account.getFirstname())
                .lastname(account.getLastname())
                .nickname(account.getNickname())
                .email(account.getEmail())
                .birthday(account.getBirthday())
                .gender(account.getGender().name())
=======
>>>>>>> src/main/java/ru/itis/og/dto/response/AccountResponse.java
                .build();
    }

    public static List<AccountResponse> from(List<Account> accounts) {
        return accounts.stream().map(AccountResponse::from).collect(Collectors.toList());
    }

}
