package ru.itis.og.service;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.og.dto.request.IdPageRequest;
import ru.itis.og.dto.response.FileInformationResponse;
import ru.itis.og.dto.response.page.FileInformationPageResponse;

import javax.servlet.http.HttpServletResponse;

public interface FileService {
    void getFile(String filename, HttpServletResponse response);

    FileInformationResponse addFile(String accountId, MultipartFile file);

    FileInformationPageResponse getFiles(IdPageRequest accountPageRequest);

    void deleteFile(IdPageRequest filePageRequest);
}
