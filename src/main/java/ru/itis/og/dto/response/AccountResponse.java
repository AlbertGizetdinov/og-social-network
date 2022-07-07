package ru.itis.og.dto.response;

import lombok.*;
import ru.itis.og.model.Account;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountResponse {

    private String email;
    private String lastname;
    private String firstname;
    private String nickname;
    private String gender;
    private Instant birthday;

    public static AccountResponse from(Account account) {
        return AccountResponse.builder()
                .email(account.getEmail())
                .lastname(account.getLastname())
                .firstname(account.getFirstname())
                .nickname(account.getNickname())
                .gender(account.getGender().name())
                .birthday(account.getBirthday())
                .build();
    }

    public static List<AccountResponse> from(List<Account> accounts) {
        return accounts.stream().map(AccountResponse::from).collect(Collectors.toList());
    }

}
