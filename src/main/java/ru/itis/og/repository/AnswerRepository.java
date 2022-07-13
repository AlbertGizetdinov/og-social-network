package ru.itis.og.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.og.model.Answer;

import java.util.UUID;

public interface AnswerRepository extends JpaRepository<Answer, UUID> {
}
