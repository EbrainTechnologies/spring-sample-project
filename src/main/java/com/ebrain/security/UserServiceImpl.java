package com.ebrain.security;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ebrain.bean.Role;
import com.ebrain.bean.User;
import com.ebrain.dao.implementation.DAORegistry;

public class UserServiceImpl implements UserDetailsService {

	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		
		//System.out.println(userName);
		User user = DAORegistry.getUserDAO().getUserByUserName(userName);
		//System.out.println(trackerUser);
		if(user == null){
			return null;
		}
		 
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
								.getRequest().getSession();
		session.setAttribute("appUser", user);
		session.setAttribute("USERID", user.getId());
		
		Boolean isAdmin =false,isManager=false,isUser =false;
		
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		if(user.getUserRole() != null){
			Role role = user.getRole();
			authorities.add(new SimpleGrantedAuthority(role.getName()));
			if(role.getName().equals("MANAGER")){
				isManager = true;
		    }else if(role.getName().equals("ADMIN")){
		    	isAdmin = true;
		    }else if(role.getName().equals("USER")){
		    	isUser = true;
		    }
		}else{
			authorities.add(new SimpleGrantedAuthority("USER"));
			isUser = true;
		}
		session.setAttribute("isManager",isManager);
		session.setAttribute("isAdmin",isAdmin);
		session.setAttribute("isUser",isUser);
		UserDetails userDetails = new org.springframework.security.core.userdetails.
				User(user.getUserName(), user.getPassword(), true, true, true, true, authorities);
		
		return userDetails;
	}

}
