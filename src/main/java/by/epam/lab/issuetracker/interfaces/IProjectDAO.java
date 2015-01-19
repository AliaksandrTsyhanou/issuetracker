package by.epam.lab.issuetracker.interfaces;

import java.util.List;

import by.epam.lab.issuetracker.entity.Project;
import by.epam.lab.issuetracker.exceptions.DAOException;

public interface IProjectDAO {

	public List<Project> getAll() throws DAOException;	
	public Project get(long id) throws DAOException;	
	public void update(Project project) throws DAOException;
	public Project add(Project project) throws DAOException;
}