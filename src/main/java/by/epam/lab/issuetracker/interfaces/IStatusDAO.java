package by.epam.lab.issuetracker.interfaces;

import java.util.List;

import by.epam.lab.issuetracker.entity.manuals.Status;
import by.epam.lab.issuetracker.exceptions.DAOException;

public interface IStatusDAO {
	
	public List<Status> getAllStatus() throws DAOException;
	public Status getStatus(int statusId) throws DAOException;
	public void updateStatus(Status status) throws DAOException;

}
