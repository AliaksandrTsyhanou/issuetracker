package by.epam.lab.issuetracker.service.dto;

public class UserAddDto extends UserEditDto{
	
	private String password;
	private String passwordConfirmation;
	

	public UserAddDto() {
		super();
	}
	
	public UserAddDto(String firstname, String lastname, String emailaddress,
			int roleId, String password, String passwordConfirmation) {
		super(firstname, lastname, emailaddress, roleId);
		this.password = password;
		this.passwordConfirmation = passwordConfirmation;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}
}
