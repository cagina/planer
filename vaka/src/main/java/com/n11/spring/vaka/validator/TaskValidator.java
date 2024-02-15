package com.n11.spring.vaka.validator;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskValidator implements ConstraintValidator<Task, String> {
    @Override
    public void initialize(Task constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null)
            return false;
        String[] splt = s.split("\n");
        Pattern pattern;
        Matcher matcher;
        for(String sp: splt) {
            pattern = Pattern.compile(" (.\\d*?)min$");
            matcher = pattern.matcher(sp);
            if (!matcher.find() && !sp.contains("lightning"))
                return false;
        }
        return true;
    }
}
