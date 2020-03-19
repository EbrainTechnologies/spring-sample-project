package com.ebrain.dao.implementation;

import java.util.Date;
import java.util.List;

import com.ebrain.bean.User;


public class  UserDAO extends HibernateDAO<Integer, User> implements com.ebrain.dao.services.UserDAO {
	
	
	public User getUserByUserName(String userName){
		return findSingle("From User where userName = ?", new Object[]{userName});
	}

/*	public void deleteUser(Integer id) {
		bulkUpdate("update User set status=?,modifiedDate=? where id=?", new Object[] {"DELETE",new Date(), id});
	}

	public boolean disableStatusUser(Integer userId,Integer companyId) {
		try {
			bulkUpdate("update User set status=?,recordAmmendedDate=?,recordAmmendedBy=? where companyId=?", new Object[] {"DISABLE",new Date(),userId,companyId});
			return true;
		} catch (Exception e) {
			return false;
			// TODO: handle exception
		}
	}
	public List<User> getAllUserDetails() {
		return find("FROM User where status=?", new Object[] { "ACTIVE" });
	}

	public List<User> getAllUserById(Integer companyId){
		return find("FROM User where   companyId=? ",new Object[]{companyId});
	}
	
	public User getUserName(String name) {
		return findSingle("from User where userName = ?" , new Object []{name});
	}
	
	public User getUserByEmail(String email) {
		return findSingle("from User where email = ?" , new Object []{email});
	}
	*/
	
}
