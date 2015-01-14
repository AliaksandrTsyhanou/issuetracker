package by.epam.lab.issuetracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.epam.lab.issuetracker.exceptions.DAOException;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserManager userManager;
	
	@Transactional
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		//return new UserDetailsImpl(userManager.getUser(username));
		try {
			return userManager.getUser(username);
		} catch (DAOException e) {
			throw new UsernameNotFoundException(e.getMessage(), e);
		}
	}

}
