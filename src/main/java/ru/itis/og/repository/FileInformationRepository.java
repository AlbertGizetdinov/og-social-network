package ru.itis.og.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.og.model.FileInformation;

import java.util.Optional;
import java.util.UUID;

public interface FileInformationRepository extends JpaRepository<FileInformation, UUID> {
    Optional<FileInformation> findByStorageFilename(String storageFilename);

    Page<FileInformation> findAllByAccount_Id(UUID id, Pageable pageable);
}
