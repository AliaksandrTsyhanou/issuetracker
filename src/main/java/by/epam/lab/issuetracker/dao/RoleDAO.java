package by.epam.lab.issuetracker.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import by.epam.lab.issuetracker.entity.Role;
import by.epam.lab.issuetracker.interfaces.IRoleDAO;

@Service
@Repository
public class RoleDAO extends AbstractDAO implements IRoleDAO{

	private static final Logger logger = LoggerFactory.getLogger(RoleDAO.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getAllRole() {
     	List<Role> listRole = new ArrayList<Role>();
    	listRole = (List<Role>) getSession().createCriteria(Role.class).list();
    	logger.debug(listRole.toString());
		return listRole;
	}

//	@Transactional
//	public List<Role> getRoles() {
//    	Query q = getSession().createQuery("from Role");
//    	List<Role> listRole = new ArrayList<Role>();
//    	listRole = (List<Role>) q.list();
//		return listRole;
//	} 

}
