package by.epam.lab.issuetracker.dao;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class AbstractDAO {
	@Inject
	private SessionFactory sessionFactory; 
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
}
