package com.ebrain.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="app_user_action")
public class UserAction implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private User user;
	private Date timeStamp;
	private String action;
	private String ipAddress;
	private String userName;
	/*private String timeStampStr;*/
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name="user_id")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Column(name="user_name")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name="time_stamp")
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	@Column(name="action")
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	@Column(name="ip_address")
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	/*@Transient()
	public String getTimeStampStr() {
		return com.ebrain.util.Util.formatDateTimeToMonthDateYearWithTwelveHourFormat(timeStamp);
	}
	
	public void setTimeStampStr(String timeStampStr) {
		this.timeStampStr = timeStampStr;
	}*/
}