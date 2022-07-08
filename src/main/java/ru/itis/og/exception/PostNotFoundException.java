package ru.itis.og.exception;

public class PostNotFoundException extends OgNotFoundException {

    public PostNotFoundException() {
        this("Post not found");
    }

    public PostNotFoundException(String message) {
        super(message);
    }

}
