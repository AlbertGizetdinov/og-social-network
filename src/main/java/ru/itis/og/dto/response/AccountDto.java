package ru.itis.og.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.og.model.Account;
import ru.itis.og.model.Gender;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private UUID id;
    private String firstname;
    private String lastname;
    private String nickname;
    private String email;
    private Instant birthday;
    private Gender gender;

    public static AccountDto from(Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .firstname(account.getFirstname())
                .lastname(account.getLastname())
                .nickname(account.getNickname())
                .email(account.getEmail())
                .birthday(account.getBirthday())
                .gender(account.getGender())
                .build();
    }

    public static List<AccountDto> from(List<Account> accounts) {
        return accounts.stream().map(AccountDto::from).collect(Collectors.toList());
    }

}
