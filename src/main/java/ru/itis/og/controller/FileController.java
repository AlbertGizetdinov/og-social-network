package ru.itis.og.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.og.api.FileApi;
import ru.itis.og.dto.request.IdPageRequest;
import ru.itis.og.dto.response.FileInformationResponse;
import ru.itis.og.dto.response.page.FileInformationPageResponse;
import ru.itis.og.service.FileService;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class FileController implements FileApi {

    private final FileService fileService;

    @Override
    public void getFile(String filename, HttpServletResponse response) {
        fileService.getFile(filename, response);
    }

    @Override
    public ResponseEntity<FileInformationResponse> addFile(String accountId, MultipartFile file) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(fileService.addFile(accountId, file));
    }

    @Override
    public ResponseEntity<FileInformationPageResponse> getFiles(IdPageRequest accountPageRequest) {
        return ResponseEntity.ok(fileService.getFiles(accountPageRequest));
    }

    @Override
    public ResponseEntity<FileInformationResponse> deleteFile(IdPageRequest filePageRequest) {
        fileService.deleteFile(filePageRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
