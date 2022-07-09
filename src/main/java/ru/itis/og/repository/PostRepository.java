package ru.itis.og.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.og.model.Post;
import ru.itis.og.model.enumeration.State;

import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {

    Page<Post> findAllByAccount_IdAndState(UUID accountId, State state, Pageable pageable);

}
