package com.ebrain.dao.implementation;

 
import com.ebrain.dao.services.RoleDAO;
import com.ebrain.dao.services.UserActionDAO;
import com.ebrain.dao.services.UserDAO;

public class DAORegistry { 
	 
	private static UserDAO userDAO; 
	private static RoleDAO roleDAO;
	private static UserActionDAO userActionDAO; 
    
	public final static UserDAO getUserDAO() {
		return userDAO;
	}

	public final void setUserDAO(UserDAO userDAO) {
		DAORegistry.userDAO = userDAO;
	}
	
	public final static RoleDAO getRoleDAO() {
		return roleDAO;
	}

	public final void setRoleDAO(RoleDAO roleDAO) {
		DAORegistry.roleDAO = roleDAO;
	}

	public final static UserActionDAO getUserActionDAO() {
		return userActionDAO;
	}

	public final void setUserActionDAO(UserActionDAO userActionDAO) {
		DAORegistry.userActionDAO = userActionDAO;
	}
}


