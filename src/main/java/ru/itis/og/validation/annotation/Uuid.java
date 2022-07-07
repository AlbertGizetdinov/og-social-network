package ru.itis.og.validation.annotation;

import ru.itis.og.validation.validator.UuidValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UuidValidator.class)
public @interface Uuid {

    String message() default "Value is not UUID";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
