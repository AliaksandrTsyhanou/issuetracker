package by.epam.lab.issuetracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import by.epam.lab.issuetracker.dao.UserDAO;
import by.epam.lab.issuetracker.entity.User;

@Service
public class UserManager {
	
	@Autowired
	private UserDAO userDAO;

	public User getUser(String username) throws UsernameNotFoundException{
		User user = userDAO.getUser(username);
		return user;
	}
	
	public User addUser(User user) throws Exception{
		userDAO.addUser(user);
		return user;		
	}
	
	public List<User> getAllUser() throws Exception{
		return userDAO.getAllUser();
	}

	public void updateUser(User user) throws Exception{
		userDAO.updateUser(user);
	}
	
	public User getUserById(long id) throws UsernameNotFoundException{
		User user = userDAO.getUserById(id);
		return user;
	}
	
}
