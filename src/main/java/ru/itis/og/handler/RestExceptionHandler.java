package ru.itis.og.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.itis.og.dto.response.ExceptionResponse;
import ru.itis.og.exception.OgForbiddenException;
import ru.itis.og.exception.OgNotFoundException;
import ru.itis.og.exception.OgServiceException;
import ru.itis.og.exception.OgUnauthorizedException;
import ru.itis.og.validation.http.ValidationErrorDto;
import ru.itis.og.validation.http.ValidationExceptionResponse;

import java.util.ArrayList;
import java.util.List;

@EnableWebMvc
@ControllerAdvice(basePackages = "ru")
@ResponseBody
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionResponse> handleValidationException(MethodArgumentNotValidException exception) {
        List<ValidationErrorDto> errors = new ArrayList<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {

            String errorMessage = error.getDefaultMessage();
            ValidationErrorDto errorDto = ValidationErrorDto.builder()
                    .message(errorMessage)
                    .build();

            if (error instanceof FieldError) {
                String fieldName = ((FieldError) error).getField();
                errorDto.setField(fieldName);
            } else {
                String objectName = error.getObjectName();
                errorDto.setObject(objectName);
            }
            errors.add(errorDto);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ValidationExceptionResponse.builder()
                        .errors(errors)
                        .build());

    }

    @ExceptionHandler(OgUnauthorizedException.class)
    public ResponseEntity<ExceptionResponse> handleUnauthorized(OgUnauthorizedException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ExceptionResponse.builder()
                        .code(exception.getHttpStatus().value())
                        .status(exception.getHttpStatus())
                        .message(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(OgForbiddenException.class)
    public ResponseEntity<ExceptionResponse> handleForbidden(OgForbiddenException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ExceptionResponse.builder()
                        .code(exception.getHttpStatus().value())
                        .status(exception.getHttpStatus())
                        .message(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(OgNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFound(OgNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ExceptionResponse.builder()
                        .code(exception.getHttpStatus().value())
                        .status(exception.getHttpStatus())
                        .message(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(OgServiceException.class)
    public ResponseEntity<ExceptionResponse> handleService(OgServiceException exception) {
        return ResponseEntity.status(exception.getHttpStatus())
                .body(ExceptionResponse.builder()
                        .code(exception.getHttpStatus().value())
                        .status(exception.getHttpStatus())
                        .message(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleUnauthorized(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ExceptionResponse.builder()
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .message(exception.getMessage())
                        .build());
    }
}
