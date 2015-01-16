package by.epam.lab.issuetracker.interfaces;

import java.util.List;

import by.epam.lab.issuetracker.entity.Resolution;
import by.epam.lab.issuetracker.exceptions.DAOException;

public interface IResolutionDAO {
	
	public List<Resolution> getAllResolution() throws DAOException;
	public Resolution getResolution(int resolutionId) throws DAOException;
	public void updateResolution(Resolution resolution) throws DAOException;
	public Resolution addResolution(Resolution resolution) throws DAOException;
}
