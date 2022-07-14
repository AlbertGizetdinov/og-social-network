package ru.itis.og.exception;

import org.springframework.http.HttpStatus;

public class OgForbiddenException extends OgServiceException {

    public OgForbiddenException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }

}
