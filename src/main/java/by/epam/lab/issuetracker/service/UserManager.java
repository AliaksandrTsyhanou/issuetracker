package by.epam.lab.issuetracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.epam.lab.issuetracker.entity.Role;
import by.epam.lab.issuetracker.entity.User;
import by.epam.lab.issuetracker.exceptions.DAOException;
import by.epam.lab.issuetracker.interfaces.IUserDAO;
import by.epam.lab.issuetracker.service.dto.ChangePasswordDto;
import by.epam.lab.issuetracker.service.dto.UserAddDto;
import by.epam.lab.issuetracker.service.dto.UserEditDto;

@Service
public class UserManager {
	
	private static final String ROLE_ADMIN = "ROLE_ADMIN";
	
	@Autowired
	private IUserDAO userDAO;

	@Transactional
	public List<User> getAllUser() throws DAOException{
		return userDAO.getAllUser();
	}	
	@Transactional
	public User getUser(String username) throws DAOException{
		User user = userDAO.getUser(username);
		return user;
	}
	@Transactional
	public User getUser(long userId) throws DAOException{
		User user = userDAO.getUserById(userId);
		return user;
	}
	@Transactional
	public UserEditDto getUserEditDto(long id) throws DAOException{
		User user = userDAO.getUserById(id);
		UserEditDto userEditDto = convertToUserEditDto(user);
		return userEditDto;
	}
	@Transactional
	public UserEditDto getUserEditDto(String username) throws DAOException{
		User user = userDAO.getUser(username);
		UserEditDto userEditDto = convertToUserEditDto(user);		
		return userEditDto;
	}
	@Transactional
	public User addUser(UserAddDto userAddDto) throws DAOException{
		User addUser = convertToUser(userAddDto);
		userDAO.addUser(addUser);
		return addUser;		
	}
	@Transactional
	public void updateUser(UserEditDto userEditDto) throws DAOException{
		updateUser(userEditDto, true);		
	}
	@Transactional
	public void updateUser(UserEditDto userEditDto, String authorizedUserName) throws DAOException{
		User authorizedUser = getUser(authorizedUserName);
		userEditDto.setUserId(authorizedUser.getId());		
		updateUser(userEditDto, isUserInRole(authorizedUser, ROLE_ADMIN));		
	}	
	@Transactional
	public void changePasswordUser(ChangePasswordDto changePasswordDto) throws DAOException {
		User user = userDAO.getUserById(changePasswordDto.getUserId());
		user.setPassword(changePasswordDto.getPassword());
		userDAO.updateUser(user);		
	}	

	
	private User convertToUser(UserAddDto userAddDto){
		User convertedUser = new User();
		convertedUser.setFirstname(userAddDto.getFirstname());
		convertedUser.setLastname(userAddDto.getLastname());
		convertedUser.setEmailaddress(userAddDto.getEmailaddress());
		convertedUser.setPassword(userAddDto.getPassword());
		Role addRole = new Role();
		addRole.setId(userAddDto.getRoleId());
		convertedUser.setRole(addRole);
		return convertedUser;
	}

	private UserEditDto convertToUserEditDto(User user){
		UserEditDto userEditDto = new UserEditDto();
		userEditDto.setUserId(user.getId());
		userEditDto.setFirstname(user.getFirstname());
		userEditDto.setLastname(user.getLastname());
		userEditDto.setEmailaddress(user.getEmailaddress());
		userEditDto.setRoleId(user.getRole().getId());
		return userEditDto;
	}
	
	private boolean isUserInRole(User user, String role){
		boolean isUserInRole = false;
		for (GrantedAuthority grantedAuthority : user.getAuthorities()) {
			System.out.println("grantedAuthority.getAuthority() = " + grantedAuthority.getAuthority());
			if (role.equals(grantedAuthority.getAuthority())){
				isUserInRole = true;
			}
		}
		return isUserInRole;
	}
	
	private void updateUser(UserEditDto userEditDto, boolean isRoleUpdated) throws DAOException{
		User user = userDAO.getUserById(userEditDto.getUserId());
		user.setFirstname(userEditDto.getFirstname());
		user.setLastname(userEditDto.getLastname());
		user.setEmailaddress(userEditDto.getEmailaddress());
		if (isRoleUpdated) {
			user.getRole().setId(userEditDto.getRoleId());
		}
		userDAO.updateUser(user);
	}
	
}