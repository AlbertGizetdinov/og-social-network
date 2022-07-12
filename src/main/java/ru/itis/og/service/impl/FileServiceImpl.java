package ru.itis.og.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.og.dto.request.IdPageRequest;
import ru.itis.og.dto.response.FileInformationResponse;
import ru.itis.og.dto.response.page.FileInformationPageResponse;
import ru.itis.og.exception.AccountNotFoundException;
import ru.itis.og.exception.FileInformationNotFoundException;
import ru.itis.og.exception.OgServiceException;
import ru.itis.og.model.Account;
import ru.itis.og.model.FileInformation;
import ru.itis.og.repository.AccountRepository;
import ru.itis.og.repository.FileInformationRepository;
import ru.itis.og.service.FileService;
import ru.itis.og.util.HttpResponseUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {

    @Value("${files.storage.path}")
    private String storagePath;

    private final FileInformationRepository fileInformationRepository;
    private final AccountRepository accountRepository;

    @Override
    public void getFile(String filename, HttpServletResponse response) {
        FileInformation fileInformation = fileInformationRepository.findByStorageFilename(filename)
                .orElseThrow(FileInformationNotFoundException::new);
        HttpResponseUtil.putFileInResponse(storagePath, fileInformation, response);
    }

    @Override
    public FileInformationResponse addFile(String accountId, MultipartFile file) {
        Account account = accountRepository.findById(UUID.fromString(accountId))
                .orElseThrow(AccountNotFoundException::new);

        String extension = Objects.requireNonNull(file.getOriginalFilename())
                .substring(file.getOriginalFilename().lastIndexOf("."));
        String storageFilename = UUID.randomUUID() + extension;

        FileInformation fileInformation = FileInformation.builder()
                .type(file.getContentType())
                .size(file.getSize())
                .originalFilename(file.getOriginalFilename())
                .storageFilename(storageFilename)
                .account(account)
                .build();

        try {
            file.transferTo(Paths.get(storagePath, storageFilename));
            fileInformation = fileInformationRepository.save(fileInformation);
        } catch (IOException e) {
            throw new OgServiceException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return FileInformationResponse.from(fileInformation);
    }

    @Override
    public FileInformationPageResponse getFiles(IdPageRequest accountPageRequest) {
        PageRequest pageRequest = PageRequest.of(
                accountPageRequest.getPage(), accountPageRequest.getSize(), Sort.by("createDate")
        );

        Page<FileInformation> files = fileInformationRepository.findAllByAccount_Id(
                UUID.fromString(accountPageRequest.getId()), pageRequest
        );

        return FileInformationPageResponse.builder()
                .files(FileInformationResponse.from(files.getContent()))
                .totalPage(files.getTotalPages())
                .build();
    }

    @Override
    public void deleteFile(IdPageRequest filePageRequest) {
        FileInformation fileInformation = fileInformationRepository.findById(UUID.fromString(filePageRequest.getId()))
                .orElseThrow(FileInformationNotFoundException::new);
        fileInformationRepository.delete(fileInformation);
    }
}
