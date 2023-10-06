package yapp.buddycon.app.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnumValidator implements ConstraintValidator<ValidatedEnum, Enum<?>> {
    private ValidatedEnum annotation;

    @Override
    public void initialize(ValidatedEnum constraintAnnotation) {
        this.annotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(Enum value, ConstraintValidatorContext context) {
        boolean result = false;
        Object[] enumValues = this.annotation.enumClass().getEnumConstants();
        if (enumValues != null) {
            for (Object enumValue : enumValues) {
                if (value == enumValue) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}
