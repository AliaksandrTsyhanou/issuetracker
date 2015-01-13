package by.epam.lab.issuetracker.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import by.epam.lab.issuetracker.entity.User;
import by.epam.lab.issuetracker.service.UserManager;
import by.epam.lab.issuetracker.service.dto.UserEditDto;

@Component
public class UserEditValidator implements Validator {

	private static final Logger logger = LoggerFactory.getLogger(UserEditValidator.class); 
	private static final String EMAIL_PATTERN = 
    		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
   	
    @Autowired
	private UserManager userManager;
    
    @Override
	public boolean supports(Class<?> clazz) {
		boolean returnValue = User.class.isAssignableFrom(clazz) || UserEditDto.class.isAssignableFrom(clazz);
		return returnValue;
	}

	@Override
	public void validate(Object target, Errors errors) {
		logger.debug("Validation edit field for user");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "user.variable.firstname.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "user.variable.lastname.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailaddress", "user.variable.emailaddress.required");
		
		UserEditDto validUser = (UserEditDto) target;
		if (!validateEmail(validUser.getEmailaddress())){
			errors.rejectValue("emailaddress", "user.variable.emailaddress.unvalid");
		};
		
		User user = userManager.getUser(validUser.getEmailaddress());
		if (user != null){
			if (user.getId() != validUser.getUserId()){
				errors.rejectValue("emailaddress", "user.variable.emailaddress.exist");
			}
		}
		
		
	}
    
    private boolean validateEmail(String email) {
    	Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    	Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    
}
