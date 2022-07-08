package ru.itis.og.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.og.api.LinkApi;
import ru.itis.og.dto.request.IdPageRequest;
import ru.itis.og.dto.request.LinkRequest;
import ru.itis.og.dto.response.LinkResponse;
import ru.itis.og.dto.response.page.LinkPageResponse;
import ru.itis.og.service.LinkService;

@RestController
@RequiredArgsConstructor
public class LinkController implements LinkApi {

    private final LinkService linkService;

    @Override
    public ResponseEntity<LinkResponse> createLink(LinkRequest linkRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(linkService.createLink(linkRequest));
    }

    @Override
    public ResponseEntity<LinkPageResponse> getLinks(IdPageRequest linkPageRequest) {
        return ResponseEntity.ok(linkService.getLinks(linkPageRequest));
    }

    @Override
    public ResponseEntity<?> deleteLink(IdPageRequest linkPageRequest) {
        linkService.deleteLink(linkPageRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
