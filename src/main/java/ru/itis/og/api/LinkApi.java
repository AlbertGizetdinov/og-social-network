package ru.itis.og.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.og.dto.request.IdPageRequest;
import ru.itis.og.dto.request.LinkRequest;
import ru.itis.og.dto.response.LinkResponse;
import ru.itis.og.dto.response.page.LinkPageResponse;

import javax.validation.Valid;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
import static ru.itis.og.constant.OgConstant.LINK_CONTROLLER_PATH;

@RequestMapping(LINK_CONTROLLER_PATH)
public interface LinkApi {

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<LinkResponse> createLink(@Valid @RequestBody LinkRequest linkRequest);

    @GetMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<LinkPageResponse> getLinks(@Valid @RequestBody IdPageRequest linkPageRequest);

    @DeleteMapping(consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<?> deleteLink(@Valid @RequestBody IdPageRequest linkPageRequest);
}
