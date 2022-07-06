package ru.itis.og.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class OgServiceException extends RuntimeException {

    private final HttpStatus httpStatus;

    public OgServiceException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

}
