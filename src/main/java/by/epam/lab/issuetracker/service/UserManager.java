package by.epam.lab.issuetracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import by.epam.lab.issuetracker.dao.UserDAO;
import by.epam.lab.issuetracker.entity.Role;
import by.epam.lab.issuetracker.entity.User;
import by.epam.lab.issuetracker.service.dto.UserAddDto;

@Service
public class UserManager {
	
	@Autowired
	private UserDAO userDAO;

	public User getUser(String username) throws UsernameNotFoundException{
		User user = userDAO.getUser(username);
		return user;
	}
	
	public User addUser(UserAddDto userAddDto) throws Exception{
		User addUser = new User();
		addUser.setFirstname(userAddDto.getFirstname());
		addUser.setLastname(userAddDto.getLastname());
		addUser.setEmailaddress(userAddDto.getEmailaddress());
		addUser.setPassword(userAddDto.getPassword());
		Role addRole = new Role();
		addRole.setId(userAddDto.getRoleId());
		addUser.setRole(addRole);
		userDAO.addUser(addUser);
		return addUser;		
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
