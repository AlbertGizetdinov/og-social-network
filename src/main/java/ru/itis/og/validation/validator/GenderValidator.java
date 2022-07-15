package ru.itis.og.validation.validator;


import ru.itis.og.model.enumeration.Gender;
import ru.itis.og.validation.annotation.ValidGender;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GenderValidator implements ConstraintValidator<ValidGender, String> {

    @Override
    public void initialize(ValidGender validGender) {
    }

    @Override
    public boolean isValid(String gender, ConstraintValidatorContext constraintValidatorContext) {
        Gender[] genders = Gender.values();
        boolean isEquals = false;
        for (Gender genderval : genders) {
            if (gender.equals(genderval.toString())) {
                isEquals = true;
                break;
            }
        }
        return isEquals;
    }
}
