package ru.itis.og.exception;

public class FileInformationNotFoundException extends OgNotFoundException {

    public FileInformationNotFoundException() {
        this("File not found");
    }

    public FileInformationNotFoundException(String message) {
        super(message);
    }
}
