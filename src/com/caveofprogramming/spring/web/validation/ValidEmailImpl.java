package com.caveofprogramming.spring.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.validator.routines.EmailValidator;

public class ValidEmailImpl implements ConstraintValidator<ValidEmail, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		EmailValidator validator = EmailValidator.getInstance();
		return validator.isValid(value);
	}

}
