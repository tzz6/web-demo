package com.tzz.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	private final static String yyyyMMdd = "yyyyMMdd";
	private final static String yyyyMMddHHmmss = "yyyyMMddHHmmss";
	private final static String yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";
	
	public static String getDateyyyyMMdd(){
		SimpleDateFormat format = new SimpleDateFormat(yyyyMMdd);
		return format.format(new Date());
	}
	
	public static String getDateyyyyMMddHHmmss(){
		SimpleDateFormat format = new SimpleDateFormat(yyyyMMddHHmmss);
		return format.format(new Date());
	}
	
	public static String getDateyyyyMMddHHmmssSSS(){
		SimpleDateFormat format = new SimpleDateFormat(yyyyMMddHHmmssSSS);
		return format.format(new Date());
	}
	
}
