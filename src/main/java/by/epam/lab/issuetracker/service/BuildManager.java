package by.epam.lab.issuetracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.epam.lab.issuetracker.entity.Build;
import by.epam.lab.issuetracker.exceptions.DAOException;
import by.epam.lab.issuetracker.interfaces.IBuildDAO;

@Service
public class BuildManager {

	@Autowired
	private IBuildDAO buildDAO;
	
	@Transactional
	public List<Build> getAll() throws DAOException {
		return buildDAO.getAll();		 
	}
	
	@Transactional
	public List<Build> getAll(long projectId) throws DAOException {
		return buildDAO.getAll(projectId);		 
	}
	
	@Transactional
	public Build get(int id) throws DAOException{
		return buildDAO.get(id);		 
	}
	
	@Transactional
	public void update(Build build) throws DAOException{
		buildDAO.update(build);
	}
	
	@Transactional
	public Build add(Build build) throws DAOException {
		return buildDAO.add(build);		 
	}
}