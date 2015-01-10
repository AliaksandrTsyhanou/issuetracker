package by.epam.lab.issuetracker.service.dto;

public class UserEditDto{

	private long userId;
	private String firstname;
	private String lastname;
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
}
