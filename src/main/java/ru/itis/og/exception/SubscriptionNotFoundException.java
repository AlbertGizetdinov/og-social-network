package ru.itis.og.exception;

public class SubscriptionNotFoundException extends OgNotFoundException {

    public SubscriptionNotFoundException() {
        this("Subscription not found");
    }

    public SubscriptionNotFoundException(String message) {
        super(message);
    }

}
