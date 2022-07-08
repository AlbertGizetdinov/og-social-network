package ru.itis.og.exception;

public class ProductNotFoundException extends OgNotFoundException {

    public ProductNotFoundException() {
        this("Product not found");
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}
