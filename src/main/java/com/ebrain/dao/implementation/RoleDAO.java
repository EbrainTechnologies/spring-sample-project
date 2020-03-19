package com.ebrain.dao.implementation;

import com.ebrain.bean.Role;


public class  RoleDAO extends HibernateDAO<Integer, Role> implements com.ebrain.dao.services.RoleDAO {
	@Override
	public Role getRoleById(Integer roleId) {

		return findSingle("FROM Role where id=?", new Object[] { roleId });
	}
	
}
