package by.epam.lab.issuetracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.epam.lab.issuetracker.entity.Project;
import by.epam.lab.issuetracker.exceptions.DAOException;
import by.epam.lab.issuetracker.interfaces.IProjectDAO;


@Service
public class ProjectManager {
	
	@Autowired
	private IProjectDAO projectDAO;
//	@Autowired
//	private IBuildDAO buildDAO;

	@Transactional
	public List<Project> getAll() throws DAOException{
		return projectDAO.getAll();
	}	
	
	@Transactional
	public Project get(long projectId) throws DAOException{
		Project project = projectDAO.get(projectId);
		return project;
	}	
	
	@Transactional
	public void update(Project project) throws DAOException{
		projectDAO.update(project);
	}
	
	@Transactional
	public Project add(Project project) throws DAOException {
//		Project addedProject = projectDAO.add(project);
		return projectDAO.add(project);		 
	}
}