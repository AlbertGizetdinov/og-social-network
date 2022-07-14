package ru.itis.og.exception;

import org.springframework.http.HttpStatus;

public class OgUnauthorizedException extends OgServiceException {

    public OgUnauthorizedException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }

}
