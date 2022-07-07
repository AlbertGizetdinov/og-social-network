package ru.itis.og.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.og.dto.request.SubscriptionPageRequest;
import ru.itis.og.dto.request.SubscriptionRequest;
import ru.itis.og.dto.response.SubscriptionResponse;
import ru.itis.og.dto.response.page.AccountPageResponse;

import javax.validation.Valid;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
import static ru.itis.og.constant.OgConstant.*;

@RequestMapping(SUBSCRIPTION_CONTROLLER_PATH)
public interface SubscriptionApi {

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<SubscriptionResponse> createSubscription(@Valid @RequestBody SubscriptionRequest subscriptionRequest);

    @GetMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<SubscriptionResponse> getSubscription(@Valid @RequestBody SubscriptionRequest subscriptionRequest);

    @DeleteMapping(consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<?> deleteSubscription(@Valid @RequestBody SubscriptionRequest subscriptionRequest);

    @GetMapping(value = FOLLOWERS_PATH, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<AccountPageResponse> getFollowers(@Valid @RequestBody SubscriptionPageRequest subscriptionPageRequest);

    @GetMapping(value = FOLLOWINGS_PATH, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<AccountPageResponse> getFollowings(@Valid @RequestBody SubscriptionPageRequest subscriptionPageRequest);

}
