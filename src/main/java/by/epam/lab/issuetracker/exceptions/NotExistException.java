package by.epam.lab.issuetracker.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = org.springframework.http.HttpStatus.NOT_FOUND)
public class NotExistException extends RuntimeException {
	private static final long serialVersionUID = 4270149543435575064L;

	public NotExistException() {
		super();
	}
	
	public NotExistException(String message) {
		super(message);
	}
}
