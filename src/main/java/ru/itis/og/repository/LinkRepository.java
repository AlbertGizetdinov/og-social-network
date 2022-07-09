package ru.itis.og.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.og.model.Link;
import ru.itis.og.model.enumeration.State;

import java.util.UUID;

public interface LinkRepository extends JpaRepository<Link, UUID> {
    Page<Link> findAllByAccount_IdAndState(UUID id, State state, Pageable pageable);
}
