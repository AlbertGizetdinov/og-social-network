package ru.itis.og.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.og.dto.request.SignUpRequest;
import ru.itis.og.dto.response.AccountResponse;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
import static ru.itis.og.constant.OgConstant.CONFIRM_CODE_PATH;
import static ru.itis.og.constant.OgConstant.SIGN_UP_CONTROLLER_PATH;


@RequestMapping(SIGN_UP_CONTROLLER_PATH)
public interface SignUpApi {

    @GetMapping(value = CONFIRM_CODE_PATH, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<String> checkConfirmCode(@PathVariable("code") @NotBlank String confirmCode);

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<AccountResponse> signUp(@Valid @RequestBody SignUpRequest signUpRequest);
}
