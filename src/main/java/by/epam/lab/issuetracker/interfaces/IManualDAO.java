package by.epam.lab.issuetracker.interfaces;

import java.util.List;

import by.epam.lab.issuetracker.enums.ManualBeanEnum;
import by.epam.lab.issuetracker.exceptions.DAOException;

public interface IManualDAO {
	
	public List<IManual> getAll(ManualBeanEnum manualBeanEnum) throws DAOException;
	public IManual get(ManualBeanEnum manualBeanEnum, int manualId) throws DAOException;
	public void update(IManual manual) throws DAOException;
	public IManual add(IManual manual) throws DAOException;
}
