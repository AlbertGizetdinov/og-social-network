package ru.itis.og.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.og.model.Description;

import java.util.UUID;

public interface DescriptionRepository extends JpaRepository<Description, UUID> {

    Description findDescriptionByAccount_Id(UUID accountId);

}
