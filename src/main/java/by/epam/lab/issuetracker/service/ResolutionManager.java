package by.epam.lab.issuetracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.epam.lab.issuetracker.entity.Resolution;
import by.epam.lab.issuetracker.exceptions.DAOException;
import by.epam.lab.issuetracker.interfaces.IResolutionDAO;

@Service
public class ResolutionManager {

	@Autowired
	private IResolutionDAO resolutionDAO;
	
	@Transactional
	public List<Resolution> getAllResolution() throws DAOException {
		return resolutionDAO.getAllResolution();		 
	}
	
	@Transactional
	public Resolution getResolution(int ResolutionId) throws DAOException{
		return resolutionDAO.getResolution(ResolutionId);		 
	}
	
	@Transactional
	public void updateResolution(Resolution resolution) throws DAOException{
		resolutionDAO.updateResolution(resolution);
	}
	
	@Transactional
	public Resolution addResolution(Resolution resolution) throws DAOException {
		return resolutionDAO.addResolution(resolution);		 
	}
}