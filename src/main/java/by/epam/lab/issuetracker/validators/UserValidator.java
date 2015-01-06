package by.epam.lab.issuetracker.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import by.epam.lab.issuetracker.entity.User;

@Component
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("ValidationUtils.rejectIfEmptyOrWhitespace(errors, firstname, First Name is required);");
	
	}

}
