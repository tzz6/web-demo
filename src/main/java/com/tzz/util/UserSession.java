package com.tzz.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.tzz.web.domain.User;

public class UserSession {

	/** 获取session中的user **/
	public static User getUser(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return (User)request.getSession().getAttribute(Constants.SESSION_KEY.CURRENT_WEB_USER);
	}
	
	/** 将user设到session中 **/
	public static void setUser(User user){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		request.getSession().setAttribute(Constants.SESSION_KEY.CURRENT_WEB_USER, user);
	}
	
	/** remove session中 user **/
	public static void removeUser(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		request.getSession().invalidate();
	}
}