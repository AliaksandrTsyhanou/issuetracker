package by.epam.lab.issuetracker.interfaces;

import java.util.List;

import by.epam.lab.issuetracker.entity.Build;
import by.epam.lab.issuetracker.exceptions.DAOException;

public interface IBuildDAO{

	public List<Build> getAll() throws DAOException;	
	public List<Build> getAll(long projectId) throws DAOException;
	public Build get(int id) throws DAOException;	
	public void update(Build build) throws DAOException;	
	public Build add(Build build) throws DAOException;
}