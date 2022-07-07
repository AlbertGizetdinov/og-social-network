package ru.itis.og.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.og.model.Subscription;

import java.util.Optional;
import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {

    Optional<Subscription> findByFollower_IdAndFollowing_Id(UUID follower, UUID following);

    Page<Subscription> findAllByFollower_Id(UUID id, Pageable pageable);

    Page<Subscription> findAllByFollowing_Id(UUID id, Pageable pageable);
}
