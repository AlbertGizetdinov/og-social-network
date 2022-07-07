package ru.itis.og.exception;

import org.springframework.http.HttpStatus;

public class AccountAlreadyExistsException extends OgServiceException {

    public AccountAlreadyExistsException(String message) { super(HttpStatus.BAD_REQUEST, message); }

}
