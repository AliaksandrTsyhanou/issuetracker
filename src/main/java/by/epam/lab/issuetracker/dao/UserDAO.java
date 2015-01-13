package by.epam.lab.issuetracker.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import by.epam.lab.issuetracker.entity.User;

@Service
@Repository
public class UserDAO {

	private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);
	
	@Inject
	private SessionFactory sessionFactory; 
	
	public Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		return session;
	   }
	
	public User addUser(User user) throws Exception {
        try {
        	logger.debug("getSession().save(user)");
        	getSession().save(user);
            return user;
        } catch (HibernateException e) {
        	throw new Exception("Could not create user " + user, e);        	
        }
    }

	public User getUser(String emailaddress) {
        try {
        	Query q = getSession().createQuery("from User where emailaddress = :emailaddress");
            q.setString("emailaddress", emailaddress);
            User user = (User) q.uniqueResult();
            return user;
        } catch (HibernateException e) {
            System.out.println("UsernameNotFoundException(User with emailaddress " + emailaddress + " not found.)");
        	throw new UsernameNotFoundException("User with emailaddress " + emailaddress + " not found.");
        }
    }
	
	public User getUserById(long id) {
		try {        	
        	User user = (User) getSession().get(User.class, id);
        	logger.debug("Get user by id: getSession().get(User.class, id)");
            return user;
        } catch (HibernateException e) {
        	logger.error("UsernameNotFoundException(User with id " + id + " not found.");
        	throw new UsernameNotFoundException("User with id " + id + " not found.)");
        }
    }

	public void deleteUser(User user) throws Exception {
        try {
            getSession().delete(user);
        } catch (HibernateException e) {
        	logger.error("Could not delete user " + user.getUsername()+ ", " + e);
        	throw new Exception("Could not delete user " + user.getUsername(), e);
        }
    }
	
	public void updateUser(User user) throws Exception {
        try {
        	logger.debug("getSession().update(user), user = " + user);
        	getSession().update(user);
        } catch (HibernateException e) {
        	logger.error("Could not update user " + user.getUsername()+ ", " + e);
        	throw new Exception("Could not update user " + user.getUsername(), e);
        }
    }
	
	
	@SuppressWarnings("unchecked")
	public List<User> getAllUser() {    	
    	List<User> listUser = new ArrayList<User>();
    	listUser = (List<User>) getSession().createCriteria(User.class).list();
		return listUser;
	}
}
