package by.epam.lab.issuetracker.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import by.epam.lab.issuetracker.entity.User;
import by.epam.lab.issuetracker.exceptions.DAOException;
import by.epam.lab.issuetracker.interfaces.IUserDAO;

@Service
@Repository
public class UserDAO extends AbstractDAO implements IUserDAO{

	private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);
	
	@Override
	public User addUser(User user) throws DAOException {
        try {
        	logger.debug("getSession().save(user)");
        	getSession().save(user);
            return user;
        } catch (HibernateException e) {
        	throw new DAOException("Could not create user " + user, e);        	
        }
    }

	@Override
	public User getUser(String emailaddress) throws DAOException {
        try {
        	Query q = getSession().createQuery("from User where emailaddress = :emailaddress");
            q.setString("emailaddress", emailaddress);
            User user = (User) q.uniqueResult();
            return user;
        } catch (HibernateException e) {
        	 logger.error("DAOException: User with emailaddress " + emailaddress + " not found. " + e);
             throw new DAOException("User with emailaddress " + emailaddress + " not found.", e);
        }
    }
	
	@Override
	public User getUserById(long id) throws DAOException {
		try {        	
        	User user = (User) getSession().get(User.class, id);
        	logger.debug("Get user by id: getSession().get(User.class, id)");
            return user;
        } catch (HibernateException e) {
        	logger.error("DAOException: User with id " + id + " not found. " + e);
        	throw new DAOException("User with id " + id + " not found.)", e);
        }
    }

	@Override
	public void deleteUser(User user) throws DAOException {
        try {
            getSession().delete(user);
        } catch (HibernateException e) {
        	logger.error("DAOException: Could not delete user " + user.getUsername()+ ", " + e);
        	throw new DAOException("Could not delete user " + user.getUsername(), e);
        }
    }
	
	@Override
	public void updateUser(User user) throws DAOException {
        try {
        	logger.debug("getSession().update(user), user = " + user);
        	getSession().update(user);
        } catch (HibernateException e) {
        	logger.error("DAOException: Could not update user " + user.getUsername()+ ", " + e);
        	throw new DAOException("Could not update user " + user.getUsername(), e);
        }
    }	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUser() throws DAOException {    	
    	try {
			List<User> listUser = new ArrayList<User>();
			listUser = (List<User>) getSession().createCriteria(User.class)
					.list();
			return listUser;
		} catch (HibernateException e) {
			throw new DAOException("List of User not found.", e);
		}
	}	
}