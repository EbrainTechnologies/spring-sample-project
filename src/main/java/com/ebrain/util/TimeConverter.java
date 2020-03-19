package com.ebrain.util;

public class TimeConverter {
	
	public static Integer miliSeconds = 1000;
	public static Integer defaultTimeInterval = 10000;
	
	public static Integer getMilliSeconds(Integer seconds){
		if(null == seconds){
			seconds = defaultTimeInterval;
		}
		seconds = seconds * miliSeconds;
		return seconds;
	}

}
