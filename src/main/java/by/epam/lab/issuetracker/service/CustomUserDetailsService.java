package by.epam.lab.issuetracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserManager userManager;
	
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		//return new UserDetailsImpl(userManager.getUser(username));
		return userManager.getUser(username);
	}

}
