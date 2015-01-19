package by.epam.lab.issuetracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.epam.lab.issuetracker.entity.manuals.Status;
import by.epam.lab.issuetracker.exceptions.DAOException;
import by.epam.lab.issuetracker.interfaces.IStatusDAO;

@Service
public class StatusManager {

	@Autowired
	private IStatusDAO statusDAO;
	
	@Transactional
	public List<Status> getAllStatus() throws DAOException {
		return statusDAO.getAllStatus();		 
	}
	
	@Transactional
	public Status getStatus(int statusId) throws DAOException{
		return statusDAO.getStatus(statusId);		 
	}
	
	@Transactional
	public void updateStatus(Status status) throws DAOException{
		statusDAO.updateStatus(status);
	}
}