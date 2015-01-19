package by.epam.lab.issuetracker.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = org.springframework.http.HttpStatus.NOT_FOUND)
public class ManualNotExistException extends RuntimeException {
	private static final long serialVersionUID = 4270149543435575064L;

	public ManualNotExistException() {
		super();
	}
	
	public ManualNotExistException(String message) {
		super(message);
	}
}
