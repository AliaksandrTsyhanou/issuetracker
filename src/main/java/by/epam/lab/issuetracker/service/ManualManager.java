package by.epam.lab.issuetracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.epam.lab.issuetracker.enums.ManualBeanEnum;
import by.epam.lab.issuetracker.exceptions.DAOException;
import by.epam.lab.issuetracker.interfaces.IManual;
import by.epam.lab.issuetracker.interfaces.IManualDAO;

@Service
public class ManualManager {

	@Autowired
	private IManualDAO manualDAO;
	
	@Transactional
	public List<IManual> getAll(String manualName) throws DAOException {
		return manualDAO.getAll(getManualBeanEnum(manualName));		 
	}
	
	@Transactional
	public IManual get(String manualName, int manualId) throws DAOException{
		return manualDAO.get(getManualBeanEnum(manualName), manualId);		 
	}
	
	@Transactional
	public void update(IManual manualDto, String manualname) throws DAOException{
		IManual updatedManual = getManual(manualDto, manualname);
		manualDAO.update(updatedManual);
	}
	
	@Transactional
	public IManual add(IManual manualDto, String manualname)  throws DAOException {
		IManual uaddedManual = getManual(manualDto, manualname);
		return manualDAO.add(uaddedManual);	
	}
	

	private ManualBeanEnum getManualBeanEnum(String manualName){
		String upperManualName = manualName.toUpperCase();
		ManualBeanEnum manualBeanEnum = ManualBeanEnum.valueOf(upperManualName);
		return manualBeanEnum;
	}
	
	private IManual getManual(IManual manualDto, String manualname) throws DAOException{
		IManual returnManual;
		try {
			returnManual = (IManual) getManualBeanEnum(manualname).getClazz().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new DAOException("Error created Bean", e);
		}
		returnManual.setId(manualDto.getId());
		returnManual.setName(manualDto.getName());
		return returnManual;
	}


}