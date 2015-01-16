package by.epam.lab.issuetracker.exceptions;

public class EmailExistsException extends Throwable {
	private static final long serialVersionUID = 4270149543435575064L;

	public EmailExistsException() {
		super();
	}
	
	public EmailExistsException(String message) {
		super(message);
	}
}
