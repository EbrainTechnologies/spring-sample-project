package com.ebrain.dao.implementation;

import com.ebrain.bean.UserAction;


public class  UserActionDAO extends HibernateDAO<String, UserAction> implements com.ebrain.dao.services.UserActionDAO {
	
	public UserAction getUserByUsernameOrEmail(String userName, String email){
		return findSingle("From UserAction where (userName = ? or email = ?) and status = ?", new Object[]{userName, email, true});
	}
}
