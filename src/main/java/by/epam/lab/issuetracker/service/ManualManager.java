package by.epam.lab.issuetracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.epam.lab.issuetracker.enums.ManualBeanEnum;
import by.epam.lab.issuetracker.exceptions.DAOException;
import by.epam.lab.issuetracker.exceptions.NotExistException;
import by.epam.lab.issuetracker.interfaces.IManual;
import by.epam.lab.issuetracker.interfaces.IManualDAO;

@Service
public class ManualManager {

	@Autowired
	private IManualDAO manualDAO;
	
	@Transactional
	public List<IManual> getAll(String manualName) throws DAOException, NotExistException {
		return manualDAO.getAll(getManualBeanEnum(manualName));		 
	}
	
	@Transactional
	public List<IManual> getAll(ManualBeanEnum manualBeanEnum) throws DAOException {
		return manualDAO.getAll(manualBeanEnum);		 
	}
	
	@Transactional
	public IManual get(String manualName, int manualId) throws DAOException, NotExistException{
		return manualDAO.get(getManualBeanEnum(manualName), manualId);		 
	}
	
	@Transactional
	public void update(IManual manualDto, String manualName) throws DAOException, NotExistException{
		ManualBeanEnum manualBeanEnum = getManualBeanEnum(manualName);
		IManual updatedManual = fillManual(manualDto, manualBeanEnum);
		manualDAO.update(updatedManual);
	}
	
	@Transactional
	public IManual add(IManual manualDto, String manualName)  throws DAOException, NotExistException {
		ManualBeanEnum manualBeanEnum = getManualBeanEnum(manualName);
		if (!manualBeanEnum.isAllowAdditions()){
			throw new NotExistException("Addition it is forbidden.");
		}
		IManual uaddedManual = fillManual(manualDto, manualBeanEnum);
		
		return manualDAO.add(uaddedManual);	
	}
	
	public boolean isAllowAdditions (String manualName){
		ManualBeanEnum manualBeanEnum = getManualBeanEnum(manualName);
		return manualBeanEnum.isAllowAdditions();
	}
	

	private ManualBeanEnum getManualBeanEnum(String manualName) throws NotExistException{
		String upperManualName = manualName.toUpperCase();
		ManualBeanEnum manualBeanEnum;
		try {
			manualBeanEnum = ManualBeanEnum.valueOf(upperManualName);
		} catch (IllegalArgumentException e) {
			throw new NotExistException(e.getMessage());
		}
		return manualBeanEnum;
	}
	
	private IManual fillManual(IManual manualDto, ManualBeanEnum manualBeanEnum) throws DAOException, NotExistException{
		IManual returnManual;
		try {
			returnManual = (IManual) manualBeanEnum.getClazz().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new DAOException("Error created Bean", e);
		}
		returnManual.setId(manualDto.getId());
		returnManual.setName(manualDto.getName());
		return returnManual;
	}


}