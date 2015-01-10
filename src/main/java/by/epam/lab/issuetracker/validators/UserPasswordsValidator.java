package by.epam.lab.issuetracker.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import by.epam.lab.issuetracker.service.dto.UserAddDto;

@Component
public class UserPasswordsValidator implements Validator {
	
	 private static final String PASSWORD_PATTERN = "^([\\w@%$\\.\\;\\,\\-]){5,}$";

	@Override
	public boolean supports(Class<?> clazz) {
		System.out.println("clazz.getName() = " + clazz.getName());
		boolean returnValue = UserAddDto.class.isAssignableFrom(clazz);
		System.out.println("UserAddDto.class.isAssignableFrom(clazz) =" + returnValue);
		return returnValue;
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("++++++password confirmation");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "user.variable.password.required");
		UserAddDto validUserAddDto = (UserAddDto) target;
		if (!validatePassword(validUserAddDto.getPassword())){
			errors.rejectValue("password", "user.variable.password.unvalid");
		};
		if (!validUserAddDto.getPassword().equals(validUserAddDto.getPasswordConfirmation())){
			errors.rejectValue("passwordConfirmation", "user.variable.passwordConfirmation.notEqual");
		}
	}   
	
	
	private boolean validatePassword(String password) {
    	Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
    	Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    
}
