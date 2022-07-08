package ru.itis.og.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.itis.og.dto.request.page.SubscriptionPageRequest;
import ru.itis.og.dto.response.AccountResponse;
import ru.itis.og.dto.response.page.AccountPageResponse;
import ru.itis.og.model.Account;
import ru.itis.og.model.Subscription;
import ru.itis.og.repository.SubscriptionRepository;
import ru.itis.og.service.AccountService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final SubscriptionRepository subscriptionRepository;

    @Override
    public AccountPageResponse getFollowers(SubscriptionPageRequest subscriptionPageRequest) {
        PageRequest pageRequest = PageRequest.of(subscriptionPageRequest.getPage(), subscriptionPageRequest.getSize());
        Page<Account> accounts = subscriptionRepository.findAllByFollowing_Id(
                        UUID.fromString(subscriptionPageRequest.getUuid()), pageRequest)
                .map(Subscription::getFollower);

        return AccountPageResponse.builder()
                .accounts(AccountResponse.from(accounts.getContent()))
                .totalPage(accounts.getTotalPages())
                .build();
    }

    @Override
    public AccountPageResponse getFollowings(SubscriptionPageRequest subscriptionPageRequest) {
        PageRequest pageRequest = PageRequest.of(subscriptionPageRequest.getPage(), subscriptionPageRequest.getSize());
        Page<Account> accounts = subscriptionRepository.findAllByFollower_Id(
                        UUID.fromString(subscriptionPageRequest.getUuid()), pageRequest)
                .map(Subscription::getFollowing);

        return AccountPageResponse.builder()
                .accounts(AccountResponse.from(accounts.getContent()))
                .totalPage(accounts.getTotalPages())
                .build();
    }
}
