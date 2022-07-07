package ru.itis.og.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.og.dto.request.SubscriptionRequest;
import ru.itis.og.dto.response.SubscriptionResponse;
import ru.itis.og.exception.AccountNotFoundException;
import ru.itis.og.exception.SubscriptionNotFoundException;
import ru.itis.og.model.Account;
import ru.itis.og.model.Subscription;
import ru.itis.og.repository.AccountRepository;
import ru.itis.og.repository.SubscriptionRepository;
import ru.itis.og.service.SubscriptionService;

import java.util.UUID;

import static ru.itis.og.dto.response.SubscriptionResponse.from;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final AccountRepository accountRepository;
    private final SubscriptionRepository subscriptionRepository;

    @Override
    public SubscriptionResponse createSubscription(SubscriptionRequest subscriptionRequest) {
        Account follower = accountRepository.findById(UUID.fromString(subscriptionRequest.getFromUuid()))
                .orElseThrow(AccountNotFoundException::new);
        Account following = accountRepository.findById(UUID.fromString(subscriptionRequest.getToUuid()))
                .orElseThrow(AccountNotFoundException::new);

        return from(subscriptionRepository.save(Subscription.builder()
                .follower(follower)
                .following(following)
                .build()));
    }

    @Override
    public SubscriptionResponse getSubscription(SubscriptionRequest subscriptionRequest) {
        return subscriptionRepository.findByFollower_IdAndFollowing_Id(
                UUID.fromString(subscriptionRequest.getFromUuid()),
                UUID.fromString(subscriptionRequest.getToUuid())
        ).map(SubscriptionResponse::from).orElseThrow(SubscriptionNotFoundException::new);
    }

    @Override
    public void deleteSubscription(SubscriptionRequest subscriptionRequest) {
        Subscription subscription = subscriptionRepository.findByFollower_IdAndFollowing_Id(
                UUID.fromString(subscriptionRequest.getFromUuid()),
                UUID.fromString(subscriptionRequest.getToUuid())
        ).orElseThrow(SubscriptionNotFoundException::new);
        subscriptionRepository.delete(subscription);
    }
}
