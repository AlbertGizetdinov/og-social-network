package ru.itis.og.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.og.api.DescriptionApi;
import ru.itis.og.dto.request.DescriptionRequest;
import ru.itis.og.dto.response.DescriptionResponse;
import ru.itis.og.service.DescriptionService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class DescriptionController implements DescriptionApi {

    private final DescriptionService descriptionService;

    @Override
    public ResponseEntity<DescriptionResponse> addDescription(DescriptionRequest descriptionRequest, UUID accountId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(descriptionService.addDescription(descriptionRequest, accountId));
    }

    @Override
    public ResponseEntity<DescriptionResponse> updateDescription(DescriptionRequest descriptionRequest, UUID accountId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(descriptionService.updateDescription(descriptionRequest, accountId));
    }

    @Override
    public ResponseEntity<DescriptionResponse> getDescription(UUID accountId) {
        return ResponseEntity.ok(descriptionService.getDescription(accountId));
    }
}
