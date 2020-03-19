package com.ebrain.controller;

import java.text.SimpleDateFormat;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ebrain.bean.Role;
import com.ebrain.bean.User;
import com.ebrain.dao.implementation.DAORegistry;

/*
* Author : Ulaganathan
*/
@Controller
@RequestMapping({"/", "/Home","/Dashboard","/Registration","/CreateAccount","/Logout" })

public class HomeController {

	@RequestMapping(value = {"/Login"}, method=RequestMethod.GET)
	public String loadHomePage(HttpServletRequest request, HttpServletResponse response, ModelMap map, HttpSession session){
		
		try{
			String error = request.getParameter("error");
			map.put("error", error);
			User user = (User)session.getAttribute("appUser");
			if(user != null ){
				session.setAttribute("appUser", null);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "page-login";
	}
	
	@RequestMapping(value = {"/Home","/"}, method=RequestMethod.GET)
	public String loadHome(HttpServletRequest request, HttpServletResponse response, ModelMap map, HttpSession session){
		try {
			Collection<User> users = DAORegistry.getUserDAO().getAll();
			map.put("userList", users);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "page-home";
	}
	
	
	@RequestMapping(value = {"/Registration"}, method=RequestMethod.GET)
	public String registration(HttpServletRequest request, HttpServletResponse response, ModelMap map, HttpSession session){
		
		Collection<Role> roles = DAORegistry.getRoleDAO().getAll();
		map.put("roleList", roles);
		  
		return "page-register";
	}
	
	
	@RequestMapping(value = {"/CreateAccount"}, method=RequestMethod.POST )
	public String saveUser(HttpServletRequest request, HttpServletResponse response, ModelMap map, Integer contactId)
			throws Exception {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String position = request.getParameter("position");
			String role = request.getParameter("role");
			String address = request.getParameter("address");
			String telephone = request.getParameter("telephone");
			String emergencyContact = request.getParameter("emergencyContact");
			String contact = request.getParameter("contact"); 
			HttpSession session = request.getSession(true); 
			User userObj = new User();
			userObj.setFirstName(firstName);
			userObj.setLastName(lastName);
			userObj.setAddress(address);
			//userObj.setPassword(password);
			userObj.setAndEncryptPassword(password);
			userObj.setContactNumber(contact);
			userObj.setEmergencyContact(emergencyContact);
			userObj.setPosition(position);
			userObj.setTelephone(telephone);
			userObj.setUserName(username);
			userObj.setUserRole(Integer.parseInt(role.trim()));
			DAORegistry.getUserDAO().saveOrUpdate(userObj);
			map.put("showSuccessMsg", true);
			map.put("successMsg", "Account Created Successfully. Please login.!");
			return "page-register";
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "page-register";

	}
	 
	  
	@RequestMapping({ "/Logout" })
	public String logout(HttpServletRequest request, HttpServletResponse response, ModelMap map) throws Exception {
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("appUser");
		if(user != null){
			session.removeAttribute("appUser");
			session.removeAttribute("USERID"); 
		}
		return "page-login";
	}
	 

}
