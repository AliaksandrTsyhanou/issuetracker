package by.epam.lab.issuetracker.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import by.epam.lab.issuetracker.service.dto.IPasswordConfirmation;

@Component
public class UserPasswordValidator implements Validator {
	
	private static final Logger logger = LoggerFactory.getLogger(UserPasswordValidator.class); 
	private static final String PASSWORD_PATTERN = "^([\\w@%$\\.\\;\\,\\-]){5,}$";

	@Override
	public boolean supports(Class<?> clazz) {
		boolean returnValue = IPasswordConfirmation.class.isAssignableFrom(clazz);
		return returnValue;
	}

	@Override
	public void validate(Object target, Errors errors) {
		logger.debug("password confirmation");
		IPasswordConfirmation validPassword = (IPasswordConfirmation) target;
		if (!validatePassword(validPassword.getPassword())){
			errors.rejectValue("password", "user.password.pattern");
		};
		if (!validPassword.getPassword().equals(validPassword.getPasswordConfirmation())){
			errors.rejectValue("passwordConfirmation", "user.passwordConfirmation.notEqual");
		}		
	}   
		
	private boolean validatePassword(String password) {
    	Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
    	Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    
}
