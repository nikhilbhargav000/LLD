package com.example.parking.system;

public class CommonUtil {
	
	public static Integer getInteger (String str) {
		Integer value = null;
		
		try {
			value = Integer.parseInt(str);
		} catch (Exception e) {
			value = null;
		}
		
		return value;
	}
	
	public static boolean isNotEmpty(String str) {
		if (str != null && str.length() > 0)
			return true;
		return false;
	}
	
}
