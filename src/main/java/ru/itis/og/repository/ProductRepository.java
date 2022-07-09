package ru.itis.og.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.og.model.Product;
import ru.itis.og.model.enumeration.State;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    Page<Product> findAllByAccount_IdAndState(UUID accountId, State state, Pageable pageable);

}
