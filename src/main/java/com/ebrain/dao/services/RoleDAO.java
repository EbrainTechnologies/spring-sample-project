package com.ebrain.dao.services;

import com.ebrain.bean.Role;


public interface RoleDAO extends RootDAO<Integer, Role> {
	public Role getRoleById(Integer roleId);
	

}
