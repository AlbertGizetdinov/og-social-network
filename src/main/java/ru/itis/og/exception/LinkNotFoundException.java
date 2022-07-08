package ru.itis.og.exception;

public class LinkNotFoundException extends OgNotFoundException {
    public LinkNotFoundException() {
        this("Link not found");
    }

    public LinkNotFoundException(String message) {
        super(message);
    }
}
