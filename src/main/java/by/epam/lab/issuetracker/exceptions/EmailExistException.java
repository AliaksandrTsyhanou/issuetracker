package by.epam.lab.issuetracker.exceptions;

public class EmailExistException extends Throwable {
	private static final long serialVersionUID = 4270149543435575064L;

	public EmailExistException() {
		super();
	}
	
	public EmailExistException(String message) {
		super(message);
	}
}
