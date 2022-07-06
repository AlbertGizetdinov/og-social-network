package ru.itis.og.exception;

public class AccountNotFoundException extends OgNotFoundException {

    public AccountNotFoundException() {
        this("Account not found");
    }

    public AccountNotFoundException(String message) {
        super(message);
    }

}
