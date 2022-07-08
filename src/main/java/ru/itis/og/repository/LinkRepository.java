package ru.itis.og.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.og.model.Link;

import java.util.UUID;

public interface LinkRepository extends JpaRepository<Link, UUID> {
    Page<Link> findAllByAccount_Id(UUID id, Pageable pageable);
}
