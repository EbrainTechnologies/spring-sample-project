package com.ebrain.util;

import org.springframework.beans.factory.InitializingBean;

public class Property implements InitializingBean {

	private String emailFrom;
	private String emailBcc;
	private String filePath;
	
	public void afterPropertiesSet() throws Exception {
		
	}
	
	public String getEmailFrom() {
		if(emailFrom == null || emailFrom.trim().isEmpty()){
			emailFrom = "ulaganathantoall@gmail.com";
		}
		return emailFrom;
	}

	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

	public String getEmailBcc() {
		return emailBcc;
	}

	public void setEmailBcc(String emailBcc) {
		this.emailBcc = emailBcc;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
}