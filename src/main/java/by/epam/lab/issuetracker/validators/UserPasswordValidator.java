package by.epam.lab.issuetracker.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import by.epam.lab.issuetracker.service.dto.IPasswordConfirmation;

@Component
public class UserPasswordValidator implements Validator {
	
	 private static final String PASSWORD_PATTERN = "^([\\w@%$\\.\\;\\,\\-]){5,}$";

	@Override
	public boolean supports(Class<?> clazz) {
		System.out.println("clazz.getName() = " + clazz.getName());
		boolean returnValue = IPasswordConfirmation.class.isAssignableFrom(clazz);
		System.out.println("UserAddDto.class.isAssignableFrom(clazz) =" + returnValue);
		return returnValue;
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("++++++password confirmation");
		IPasswordConfirmation validPassword = (IPasswordConfirmation) target;
		if (!validatePassword(validPassword.getPassword())){
			errors.rejectValue("password", "user.variable.password.unvalid");
		};
		if (!validPassword.getPassword().equals(validPassword.getPasswordConfirmation())){
			errors.rejectValue("passwordConfirmation", "user.variable.passwordConfirmation.notEqual");
		}
		System.out.println("validPassword.getUserId(); = " + validPassword.getUserId());
		System.out.println("validate errors.hasErrors() = " + errors.hasErrors());
		System.out.println("-----password confirmation");
	}   
	
	
	private boolean validatePassword(String password) {
    	Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
    	Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    
}
