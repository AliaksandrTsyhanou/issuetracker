package by.epam.lab.issuetracker.service;

import by.epam.lab.issuetracker.entity.User;

public class UserAddDto extends User{

	private static final long serialVersionUID = -6447918532377275024L;
	private String passwordConfirmation;

	public UserAddDto() {
		super();
	}

	public UserAddDto(String emailaddress, String password, String firstname,
			String lastname, String passwordConfirmation) {
		super(emailaddress, password, firstname, lastname);
		this.passwordConfirmation = passwordConfirmation;
	}
	
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}
	
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}
	

}
