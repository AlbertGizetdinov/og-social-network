package ru.itis.og.validation.validator;

import ru.itis.og.validation.annotation.Birthday;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BirthdayValidator implements ConstraintValidator<Birthday, String> {

    @Override
    public boolean isValid(String birthday, ConstraintValidatorContext constraintValidatorContext) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            sdf.parse(birthday);
            return true;
        } catch (ParseException e) {
            return false;
        }

    }
}
