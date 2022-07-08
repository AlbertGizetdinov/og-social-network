package ru.itis.og.service;

import ru.itis.og.dto.request.IdPageRequest;
import ru.itis.og.dto.response.page.AccountPageResponse;

public interface AccountService {

    AccountPageResponse getFollowers(IdPageRequest idPageRequest);

    AccountPageResponse getFollowings(IdPageRequest idPageRequest);

}
