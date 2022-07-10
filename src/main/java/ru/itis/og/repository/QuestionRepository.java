package ru.itis.og.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.og.model.Question;

import java.util.List;
import java.util.UUID;

public interface QuestionRepository extends JpaRepository<Question, UUID> {

    List<Question> findAllByIsAnsweredTrue();

    List<Question> findAllByIsAnsweredFalse();
}
