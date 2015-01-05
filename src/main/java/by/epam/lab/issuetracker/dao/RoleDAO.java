package by.epam.lab.issuetracker.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.epam.lab.issuetracker.entity.Role;

@Service
@Repository
public class RoleDAO {
	
	@Inject
	private SessionFactory sessionFactory; 
	
	public Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		return session;
	   }
	
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Role> getAllRole() {
    	
    	List<Role> listRole = new ArrayList<Role>();
    	listRole = (List<Role>) getSession().createCriteria(Role.class).list();
    	System.out.println(listRole);
		return listRole;
	}
	
//	@Transactional
//	public List<Role> getRoles() {
//    	Query q = getSession().createQuery("from Role");
//    	List<Role> listRole = new ArrayList<Role>();
//    	listRole = (List<Role>) q.list();
//		return listRole;
//	} 

}
