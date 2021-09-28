package ru.job4j.chat.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValueValidValidator implements ConstraintValidator<ValueValid, Long> {

    private int value;

    @Override
    public void initialize(ValueValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        value = constraintAnnotation.setValue();
    }

    @Override
    public boolean isValid(Long number, ConstraintValidatorContext constraintValidatorContext) {
        return number == value;
    }
}
