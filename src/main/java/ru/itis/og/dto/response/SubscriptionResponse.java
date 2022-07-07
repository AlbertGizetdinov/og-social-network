package ru.itis.og.dto.response;

import lombok.*;
import ru.itis.og.model.Subscription;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionResponse {

    private UUID follower;
    private UUID following;
    private Instant createdAt;

    public static SubscriptionResponse from(Subscription subscription) {
        return SubscriptionResponse.builder()
                .follower(subscription.getFollower().getId())
                .following(subscription.getFollowing().getId())
                .createdAt(subscription.getCreateDate())
                .build();
    }

    public static List<SubscriptionResponse> from(List<Subscription> subscriptions) {
        return subscriptions.stream().map(SubscriptionResponse::from).collect(Collectors.toList());
    }

}
