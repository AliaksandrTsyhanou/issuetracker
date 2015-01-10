package by.epam.lab.issuetracker.service.dto;

public class ChangePasswordDto implements IPasswordConfirmation {
	
	private long UserId;
	private String password;
	private String passwordConfirmation;
	

	public ChangePasswordDto() {
		super();
	}
	
	public ChangePasswordDto(String password, String passwordConfirmation) {
		super();
		this.password = password;
		this.passwordConfirmation = passwordConfirmation;
	}

	@Override
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

	public long getUserId() {
		return UserId;
	}

	public void setUserId(long userId) {
		UserId = userId;
	}
}
