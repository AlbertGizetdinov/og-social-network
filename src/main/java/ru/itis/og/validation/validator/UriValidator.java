package ru.itis.og.validation.validator;

import ru.itis.og.validation.annotation.Uri;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.net.URI;
import java.net.URISyntaxException;

public class UriValidator implements ConstraintValidator<Uri, String> {
    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {
        try {
            new URI(string);
            return true;
        } catch (URISyntaxException exception) {
            return false;
        }
    }
}
