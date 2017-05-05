package com.app.utils.annotatitons;

import com.app.models.binding.RegisterBinding;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * Created by vilimir on 17.04.17.
 */
public class IsPasswordMatchingValidator implements ConstraintValidator<IsPasswordMatching, Object> {
    @Override
    public void initialize(IsPasswordMatching isPasswordMatching) {

    }

    @Override
    public boolean isValid(Object userClass, ConstraintValidatorContext constraintValidatorContext) {
        if (userClass instanceof RegisterBinding) {
            return ((RegisterBinding) userClass).getPassword().equals(((RegisterBinding) userClass).getConfirmPassword());
        }

        return false;
    }

}

