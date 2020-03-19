package com.ebrain.bean;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.codec.binary.Hex;

import com.ebrain.dao.implementation.DAORegistry;

@Entity
@Table(name="app_users")
public class User implements Serializable {

	private Integer id;
	private String userName;
	private String password;
	private String firstName;	
	private String lastName;
	private String position;
	private String address;
	private String telephone;
	private String emergencyContact;
	private String contactNumber;
	private Integer userRole;
	private Set<Role> roles; 
	private Role role;  
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="user_name")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name="password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	 
	@Column(name="user_role")
	public Integer getUserRole() {
		return userRole;
	}
	public void setUserRole(Integer userRole) {
		this.userRole = userRole;
	}
	
	
	@Transient
	public void setAndEncryptPassword(String password) {
	     MessageDigest algorithm;
		try {
			algorithm = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			
			throw new RuntimeException("SHA-1 algorithm not found!");
		}
	    byte [] digest = algorithm.digest(password.getBytes());
	    setPassword(new String(Hex.encodeHex(digest)));
	}
	 
	@Transient
	public boolean hasRole(String roleName) {
		for (Role role:roles) {
			if (role.getName().equals(roleName)) {
				return true;
			}
		}
		return false;
	}
	
	@Transient
	public boolean hasRoles(String roleName, String roleName2) {
		for (Role role:roles) {
			if (role.getName().equals(roleName) || role.getName().equals(roleName2)) {
				return true;
			}
		}
		return false;
	} 
	  
	@Transient
	public Role getRole() {
		if(null != userRole){
			role = DAORegistry.getRoleDAO().get(userRole);
		}
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	@Column(name="first_name")
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column(name="last_name")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Column(name="position")
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	@Column(name="address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name="telephone")
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	@Column(name="emergency_contact_no")
	public String getEmergencyContact() {
		return emergencyContact;
	}
	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}
	
	@Column(name="contact_no")
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	public static void main(String[] args) {
		
		User set = new User();
		
		set.setAndEncryptPassword("admin");
		
		System.out.println(set.getPassword());
	}
	
}
