package by.epam.lab.issuetracker.service.dto;

public interface IPasswordConfirmation {
	
	public long getUserId();
	public void setUserId(long id);

	public String getPassword();
	public void setPassword(String password);
	public String getPasswordConfirmation();
	public void setPasswordConfirmation(String passwordConfirmation);

}
