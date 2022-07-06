package ru.itis.og.validation.validator;

import ru.itis.og.validation.annotation.ValidBirthday;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BirthdayValidator implements ConstraintValidator<ValidBirthday, String> {

    @Override
    public void initialize(ValidBirthday validBirthday) {
    }

    @Override
    public boolean isValid(String birthday, ConstraintValidatorContext constraintValidatorContext) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            sdf.parse(birthday);
            return true;
        } catch (ParseException e) {
            return false;
        }

    }
}
