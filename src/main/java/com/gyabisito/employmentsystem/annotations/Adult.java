package com.gyabisito.employmentsystem.annotations;

import com.gyabisito.employmentsystem.validators.AdultValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AdultValidator.class)
@Documented
public @interface Adult {
    String message() default "El empleado debe tener al menos 18 años";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}