package ru.itis.og.exception;

public class ReactionNotFoundException extends OgNotFoundException {

    public ReactionNotFoundException() {
        this("Reaction not found");
    }

    public ReactionNotFoundException(String message) {
        super(message);
    }

}
