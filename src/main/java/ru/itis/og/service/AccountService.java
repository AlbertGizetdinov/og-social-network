package ru.itis.og.service;

import ru.itis.og.dto.request.page.SubscriptionPageRequest;
import ru.itis.og.dto.response.page.AccountPageResponse;

public interface AccountService {

    AccountPageResponse getFollowers(SubscriptionPageRequest subscriptionPageRequest);

    AccountPageResponse getFollowings(SubscriptionPageRequest subscriptionPageRequest);

}
