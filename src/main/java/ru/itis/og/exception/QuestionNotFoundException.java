package ru.itis.og.exception;

public class QuestionNotFoundException extends OgNotFoundException {

    public QuestionNotFoundException() { this("Question not found"); }

    public QuestionNotFoundException(String message) { super(message); }
}
