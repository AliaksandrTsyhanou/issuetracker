package by.epam.lab.issuetracker.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import by.epam.lab.issuetracker.service.dto.UserAddDto;

@Component
public class UserAddDtoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		System.out.println("clazz.getName() = " + clazz.getName());
		boolean returnValue = UserAddDto.class.isAssignableFrom(clazz);
		System.out.println("UserAddDto.class.isAssignableFrom(clazz) =" + returnValue);
//		boolean returnValue = clazz.isAssignableFrom(UserAddDto.class);
//		System.out.println("clazz.isAssignableFrom(UserAddDto.class) = " + returnValue);
//		boolean returnValue = (UserAddDto.class == clazz);
//		System.out.println("UserAddDto.class == clazz = " + returnValue);
		return returnValue;
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("++++++password confirmation");
		UserAddDto validUserAddDto = (UserAddDto) target;
		if (!validUserAddDto.getPassword().equals(validUserAddDto.getPasswordConfirmation())){
			errors.rejectValue("passwordConfirmation", "user.variable.passwordConfirmation.notEqual");
		}
	}   
    
}
