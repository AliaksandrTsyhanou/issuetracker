package by.epam.lab.issuetracker.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import by.epam.lab.issuetracker.enums.StatusEnum;
import by.epam.lab.issuetracker.service.dto.IssueDto;

@Component
public class IssueValidator implements Validator {
	
	private static final Logger logger = LoggerFactory.getLogger(IssueValidator.class); 

	@Override
	public boolean supports(Class<?> clazz) {
		boolean returnValue = IssueDto.class.isAssignableFrom(clazz);
		return returnValue;
	}

	@Override
	public void validate(Object target, Errors errors) {
		IssueDto validIssueDto = (IssueDto) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "status.id", "user.variable.required");
		
		if (!errors.hasErrors()){
			return;
		}
		int statusId = validIssueDto.getStatus().getId();
		if (statusId == StatusEnum.RESOLVED.getId()){			
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "assignee.id", "user.variable.required");
		}
		if ((statusId != StatusEnum.RESOLVED.getId()) && (statusId != StatusEnum.CLOSED.getId())){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "summary", "user.variable.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "user.variable.required");			
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "type.id", "user.variable.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "priority.id", "user.variable.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "project.id", "user.variable.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "build.id", "user.variable.required");
		}
	}
    
}
