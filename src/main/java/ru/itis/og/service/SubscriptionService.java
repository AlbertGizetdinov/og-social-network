package ru.itis.og.service;

import ru.itis.og.dto.request.SubscriptionRequest;
import ru.itis.og.dto.response.SubscriptionResponse;

public interface SubscriptionService {
    SubscriptionResponse createSubscription(SubscriptionRequest subscriptionRequest);

    SubscriptionResponse getSubscription(SubscriptionRequest subscriptionRequest);

    void deleteSubscription(SubscriptionRequest subscriptionRequest);

}
