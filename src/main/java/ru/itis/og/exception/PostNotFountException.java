package ru.itis.og.exception;

public class PostNotFountException extends OgNotFoundException {

    public PostNotFountException() {
        this("Post not found");
    }

    public PostNotFountException(String message) {
        super(message);
    }
}
