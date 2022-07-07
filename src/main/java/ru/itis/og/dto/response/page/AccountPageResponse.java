package ru.itis.og.dto.response.page;

import lombok.*;
import ru.itis.og.dto.response.AccountResponse;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountPageResponse {

    List<AccountResponse> accounts;
    int totalPage;

}
