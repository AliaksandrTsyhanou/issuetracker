package by.epam.lab.issuetracker.interfaces;

import java.util.List;

import by.epam.lab.issuetracker.entity.User;
import by.epam.lab.issuetracker.exceptions.DAOException;

public interface IUserDAO {
	
	public User addUser(User user) throws DAOException;
	public User getUser(String emailaddress) throws DAOException;
	public User getUserById(long id) throws DAOException;
	public void deleteUser(User user) throws DAOException;
	public void updateUser(User user) throws DAOException;
	public List<User> getAllUser() throws DAOException;
}
