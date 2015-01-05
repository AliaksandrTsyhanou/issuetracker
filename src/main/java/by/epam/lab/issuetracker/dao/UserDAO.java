package by.epam.lab.issuetracker.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.epam.lab.issuetracker.entity.User;

@Service
@Repository
public class UserDAO {

	@Inject
	private SessionFactory sessionFactory; 
	
	public Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		return session;
	   }
	
	@Transactional
	public User addUser(User user) throws Exception {
        try {
        	//Role defaultRole = new Role(2, "USER", "ROLE_USER");
            //User user = new User(username, password, defaultRole);
            getSession().save(user);
            return user;
        } catch (HibernateException e) {
        	throw new Exception("Could not create user " + user, e);        	
        }
    }

	@Transactional
	public User getUser(String emailaddress) {
        try {
        	Query q = getSession().createQuery("from User where emailaddress = :emailaddress");
            q.setString("emailaddress", emailaddress);
            User user = (User) q.uniqueResult();
//            HibernateTemplate hibernateTemplate = new HibernateTemplate(sessionFactory);
//            hibernateTemplate.initialize(user); 
//            System.out.println("++++++++" + user.getRole());
            return user;
        } catch (HibernateException e) {
            System.out.println("UsernameNotFoundException(User with emailaddress + emailaddress + not found.");
        	throw new UsernameNotFoundException("User with emailaddress " + emailaddress + " not found.");
        }
    }

	@Transactional
	public void deleteUser(User user) throws Exception {
        try {
            getSession().delete(user);
        } catch (HibernateException e) {
            throw new Exception("Could not delete user " + user.getUsername(), e);
        }
    }
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<User> getAllUser() {    	
    	List<User> listRole = new ArrayList<User>();
    	listRole = (List<User>) getSession().createCriteria(User.class).list();
    	System.out.println(listRole);
		return listRole;
	}
}
