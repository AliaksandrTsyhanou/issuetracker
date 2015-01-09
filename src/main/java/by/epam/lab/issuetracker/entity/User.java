package by.epam.lab.issuetracker.entity;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements UserDetails{
	private static final long serialVersionUID = -1061006057204748628L;
	private long id;
	private String emailaddress;
	private String password;
	private Role role;
	private String firstname;
	private String lastname;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String emailaddress, String password,
			String firstname, String lastname) {
		super();
		this.emailaddress = emailaddress;
		this.password = password;
		this.role = new Role();
		this.firstname = firstname;
		this.lastname = lastname;
	}

//	public User(String username, String password, Role role) {
//		super();
//		this.emailaddress = username;
//		this.setRole(role);
//		this.password = password;
//	}
	
	public String getEmailaddress() {
		return emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return emailaddress;
	}
	
	public void setUsername(String username) {
		this.emailaddress = username;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole(){
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
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
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		for (final String role : role.getValue().split(",")) {
			if (role != null && !"".equals(role.trim())) {
				GrantedAuthority grandAuthority = new GrantedAuthority() {
					private static final long serialVersionUID = 3958183417696804555L;

					public String getAuthority() {
						return role.trim();
					}
				};
				authorities.add(grandAuthority);
			}
		}
		return authorities;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", emailaddress=" + emailaddress
				+ ", password=" + password + ", role=" + role + ", firstname="
				+ firstname + ", lastname=" + lastname + "]";
	}

}