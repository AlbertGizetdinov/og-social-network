package ru.itis.og.exception;

import org.springframework.http.HttpStatus;

public class OgNotFoundException extends OgServiceException {

    public OgNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

}
