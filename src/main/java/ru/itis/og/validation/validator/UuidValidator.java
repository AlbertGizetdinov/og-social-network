package ru.itis.og.validation.validator;

import ru.itis.og.validation.annotation.Uuid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

public class UuidValidator implements ConstraintValidator<Uuid, String> {
    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {
        if (string != null) {
            try {
                UUID.fromString(string);
                return true;
            } catch (IllegalArgumentException exception) {
                return false;
            }
        } else {
            return false;
        }
    }
}
