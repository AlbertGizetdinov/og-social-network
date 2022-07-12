package ru.itis.og.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.og.dto.request.IdPageRequest;
import ru.itis.og.dto.response.FileInformationResponse;
import ru.itis.og.dto.response.page.FileInformationPageResponse;
import ru.itis.og.validation.annotation.Uuid;

import javax.servlet.http.HttpServletResponse;

import static ru.itis.og.constant.OgConstant.FILE_CONTROLLER_PATH;
import static ru.itis.og.constant.OgConstant.GET_FILE_PATH;

@RequestMapping(FILE_CONTROLLER_PATH)
public interface FileApi {

    @GetMapping(GET_FILE_PATH)
    void getFile(@PathVariable("filename") String filename, HttpServletResponse response);

    @ResponseBody
    @PostMapping
    ResponseEntity<FileInformationResponse> addFile(@RequestParam("account-id") @Uuid String accountId,
                                                    MultipartFile file);

    @ResponseBody
    @GetMapping
    ResponseEntity<FileInformationPageResponse> getFiles(@RequestBody IdPageRequest accountPageRequest);

    @ResponseBody
    @DeleteMapping
    ResponseEntity<FileInformationResponse> deleteFile(@RequestBody IdPageRequest filePageRequest);
}
