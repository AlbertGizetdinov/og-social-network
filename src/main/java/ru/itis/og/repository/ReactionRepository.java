package ru.itis.og.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.og.model.Reaction;

import java.util.Optional;
import java.util.UUID;

public interface ReactionRepository extends JpaRepository<Reaction, UUID> {

    Page<Reaction> findAllByPost_Id(UUID postId, Pageable pageable);

    Optional<Reaction> findByPost_IdAndAccount_Id(UUID postId, UUID accountId);
}
