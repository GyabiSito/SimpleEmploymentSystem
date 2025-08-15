package com.gyabisito.employmentsystem.validators;

import com.gyabisito.employmentsystem.annotations.Adult;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class AdultValidator implements ConstraintValidator<Adult, LocalDate> {

    @Override
    public boolean isValid(LocalDate birthDate, ConstraintValidatorContext context) {
        if (birthDate == null) return false;

        LocalDate today = LocalDate.now();
        LocalDate eighteenYearsAgo = today.minusYears(18);

        return birthDate.isBefore(eighteenYearsAgo) || birthDate.isEqual(eighteenYearsAgo);
    }
}