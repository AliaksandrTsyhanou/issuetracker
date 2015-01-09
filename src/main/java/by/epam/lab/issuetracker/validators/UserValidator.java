package by.epam.lab.issuetracker.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import by.epam.lab.issuetracker.entity.User;

@Component
public class UserValidator implements Validator {

    private static final String EMAIL_PATTERN = 
    		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PASSWORD_PATTERN = 
    		"^([\\w@%$\\.\\;\\,\\-]){5,}$";
	
	@Override
	public boolean supports(Class<?> clazz) {
		System.out.println("clazz.getName() = " + clazz.getName());
		boolean returnValue = User.class.isAssignableFrom(clazz);
		System.out.println("User.class.isAssignableFrom(clazz) =" + returnValue);
		return returnValue;
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("ValidationUtils.rejectIfEmptyOrWhitespace for user");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "user.variable.firstname.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "user.variable.lastname.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailaddress", "user.variable.emailaddress.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "user.variable.password.required");
		User validUser = (User) target;
		if (!validateEmail(validUser.getEmailaddress())){
			errors.rejectValue("emailaddress", "user.variable.emailaddress.unvalid");
		};
		if (!validatePassword(validUser.getPassword())){
			errors.rejectValue("password", "user.variable.password.unvalid");
		};
		
	}
    
    private boolean validateEmail(String email) {
    	Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    	Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    private boolean validatePassword(String password) {
    	Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
    	Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
