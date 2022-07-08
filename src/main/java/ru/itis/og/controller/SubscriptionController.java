package ru.itis.og.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.og.api.SubscriptionApi;
import ru.itis.og.dto.request.IdPageRequest;
import ru.itis.og.dto.request.SubscriptionRequest;
import ru.itis.og.dto.response.SubscriptionResponse;
import ru.itis.og.dto.response.page.AccountPageResponse;
import ru.itis.og.service.AccountService;
import ru.itis.og.service.SubscriptionService;

@RestController
@RequiredArgsConstructor
public class SubscriptionController implements SubscriptionApi {

    private final SubscriptionService subscriptionService;
    private final AccountService accountService;

    @Override
    public ResponseEntity<SubscriptionResponse> createSubscription(SubscriptionRequest subscriptionRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(subscriptionService.createSubscription(subscriptionRequest));
    }

    @Override
    public ResponseEntity<SubscriptionResponse> getSubscription(SubscriptionRequest subscriptionRequest) {
        return ResponseEntity.ok(subscriptionService.getSubscription(subscriptionRequest));
    }

    @Override
    public ResponseEntity<?> deleteSubscription(SubscriptionRequest subscriptionRequest) {
        subscriptionService.deleteSubscription(subscriptionRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @Override
    public ResponseEntity<AccountPageResponse> getFollowers(IdPageRequest idPageRequest) {
        return ResponseEntity.ok(accountService.getFollowers(idPageRequest));
    }

    @Override
    public ResponseEntity<AccountPageResponse> getFollowings(IdPageRequest idPageRequest) {
        return ResponseEntity.ok(accountService.getFollowings(idPageRequest));
    }

}
