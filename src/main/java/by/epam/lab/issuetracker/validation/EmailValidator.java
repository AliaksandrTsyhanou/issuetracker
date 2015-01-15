package by.epam.lab.issuetracker.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import by.epam.lab.issuetracker.validation.annotation.ValidEmail;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
	
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + 
			"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	@Override
	public void initialize(ValidEmail constraintAnnotation) {
	}
	
	@Override
	public boolean isValid(String emailaddress, ConstraintValidatorContext context) {
		boolean result = validateEmail(emailaddress);
		System.out.println("EmailValidator isValid = " + result);
		return result;
	}
	
	private boolean validateEmail(String email) {
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
}
