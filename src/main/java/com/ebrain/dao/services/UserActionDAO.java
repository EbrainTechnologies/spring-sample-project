package com.ebrain.dao.services;

import com.ebrain.bean.UserAction;


public interface UserActionDAO extends RootDAO<String, UserAction> {
	
	public UserAction getUserByUsernameOrEmail(String userName, String email);

}
