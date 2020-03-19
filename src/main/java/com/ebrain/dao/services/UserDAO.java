package com.ebrain.dao.services;

import java.util.List;


import com.ebrain.bean.User;



public interface UserDAO extends RootDAO<Integer, User> {
	
	public User getUserByUserName(String userName);
	
	/*public void deleteUser(Integer id);
	
	public List<User> getAllUserDetails();
	public List<User> getAllUserById(Integer companyId);
	public boolean disableStatusUser(Integer userId,Integer companyId);
	public User getUserName(String name);
	
	public User getUserByEmail(String email);
*/

	
}
