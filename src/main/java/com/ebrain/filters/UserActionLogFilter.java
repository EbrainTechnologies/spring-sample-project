package com.ebrain.filters;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.ebrain.bean.User;
import com.ebrain.bean.UserAction;
import com.ebrain.dao.implementation.DAORegistry;

public class UserActionLogFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		
		String path = httpServletRequest.getRequestURI().substring(httpServletRequest.getContextPath().length());
		//String path =httpServletRequest.getPathInfo();
		//System.out.println(path);
		if(path == null || path.contains("Logout") || path.contains("/resources/")){
			// skip this and do not track this
		}else{
			User user = (User)httpServletRequest.getSession().getAttribute("appUser");
			if(user != null){
				try {
					//logUserAction(user, path, httpServletRequest);
				}catch(Exception e) {
					e.printStackTrace();
				}
				
			}
		}
		chain.doFilter(request, response);
	}
	
	public boolean logUserAction(User user, String path, HttpServletRequest httpServletRequest){
		try{
			UserAction userAction = new UserAction();
			userAction.setAction(path);
			userAction.setTimeStamp(new Date());
			userAction.setUser(user);
			userAction.setIpAddress(httpServletRequest.getRemoteAddr());
			userAction.setUserName(user.getUserName());
			DAORegistry.getUserActionDAO().saveOrUpdate(userAction);
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		
		Date date = new Date();
		
		System.out.println(date);
		
	}

}
