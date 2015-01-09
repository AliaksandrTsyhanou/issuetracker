package by.epam.lab.issuetracker.service.dto;

import by.epam.lab.issuetracker.entity.User;

public class UserAddDto extends User{

	private static final long serialVersionUID = -6447918532377275024L;
	
	private String firstname;
	private String lastname;
	private String emailaddress;
	private int roleId;
	private String password;
	private String passwordConfirmation;
	

	public UserAddDto() {
		super();
	}

	public UserAddDto(String firstname, String lastname, String emailaddress,
			int roleId, String password, String passwordConfirmation) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.emailaddress = emailaddress;
		this.roleId = roleId;
		this.password = password;
		this.passwordConfirmation = passwordConfirmation;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmailaddress() {
		return emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
}
