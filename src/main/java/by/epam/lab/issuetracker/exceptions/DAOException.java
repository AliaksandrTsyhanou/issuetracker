package by.epam.lab.issuetracker.exceptions;

public class DAOException extends Exception {
	private static final long serialVersionUID = 5340959686018100299L;
    public DAOException() {}
    public DAOException(String message) {
    	super(message);
    }
    public DAOException(Throwable cause) {
    	super(cause);
    }
    public DAOException(String message, Throwable cause) {
    	super(message, cause);
    }
}