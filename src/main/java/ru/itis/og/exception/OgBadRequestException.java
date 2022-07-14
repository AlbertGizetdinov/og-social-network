package ru.itis.og.exception;

import org.springframework.http.HttpStatus;

public class OgBadRequestException extends OgServiceException {

    public OgBadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

}
