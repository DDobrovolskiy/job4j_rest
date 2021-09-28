package ru.job4j.chat.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValueValidValidator.class)
public @interface ValueValid {
    int setValue() default 0;

    String message() default "{javax.validation.constraints.ValueValid.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
