package ru.itis.og.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.og.dto.request.DescriptionRequest;
import ru.itis.og.dto.response.DescriptionResponse;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
import static ru.itis.og.constant.OgConstant.DESCRIPTION_CONTROLLER_PATH;

@RequestMapping(DESCRIPTION_CONTROLLER_PATH)
public interface DescriptionApi {

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    ResponseEntity<DescriptionResponse> addDescription(@PathVariable("account-id") UUID accountId,
                                                       @Valid @RequestBody DescriptionRequest descriptionRequest);

    @PutMapping(produces = APPLICATION_JSON_VALUE)
    ResponseEntity<DescriptionResponse> updateDescription(@Valid @RequestBody DescriptionRequest descriptionRequest,
                                                          @PathVariable("account-id") UUID accountId);

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    ResponseEntity<DescriptionResponse> getDescription(@PathVariable("account-id") UUID accountId);
}
