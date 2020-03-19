package com.ebrain.util;

import java.util.HashMap;
import java.util.Map;

import com.ebrain.bean.User;

public class ResetPasswordCacheUtil {

	public static Map<String, User> userMap = new HashMap<String, User>();
	
	public static void addResetPasswordToken(String token, User user){
		userMap.put(token.toUpperCase(), user);
	}
	
	public static User getUserByToken(String token){
		User user = userMap.get(token.toUpperCase());
		if(null == user){
			return null;
		}
		return user;
	}
	
}