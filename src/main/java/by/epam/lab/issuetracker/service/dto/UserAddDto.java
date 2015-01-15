package by.epam.lab.issuetracker.service.dto;

import javax.validation.constraints.Pattern;

public class UserAddDto extends UserEditDto implements IPasswordConfirmation {
	
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

	
	@Override
	@Pattern(regexp="^([\\w@%$\\.\\;\\,\\-]){5,}$", message="{user.password.pattern}")
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	@Override
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}
}
