package by.epam.lab.issuetracker.service.dto;

import org.hibernate.validator.constraints.NotEmpty;

import by.epam.lab.issuetracker.validation.annotation.ValidEmail;

public class UserEditDto{

	private long userId;
	@NotEmpty(message="{user.firstname.required}")
	private String firstname;
	@NotEmpty(message="{user.lastname.required}")
	private String lastname;
	@ValidEmail
	private String emailaddress;
	private int roleId;
	

	public UserEditDto() {
		super();
	}

	public UserEditDto(String firstname, String lastname, String emailaddress,
			int roleId) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.emailaddress = emailaddress;
		this.roleId = roleId;
	}
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long id) {
		this.userId = id;
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

	@Override
	public String toString() {
		return "UserEditDto [userId=" + userId + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", emailaddress=" + emailaddress
				+ ", roleId=" + roleId + "]";
	}	
}
