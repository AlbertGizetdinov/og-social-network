package ru.itis.og.exception;

public class DescriptionNotFoundException extends OgNotFoundException {

    public DescriptionNotFoundException() { this("Description not found"); }

    public DescriptionNotFoundException(String message) { super(message); }

}
