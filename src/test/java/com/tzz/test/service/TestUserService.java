package com.tzz.test.service;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tzz.BaseTest;
import com.tzz.web.domain.User;
import com.tzz.web.service.UserService;

public class TestUserService extends BaseTest{

	@Autowired
	private UserService userService;
	
	@Test
	public void testAddUser(){
		User user = new User();
		user.setName("测试AOP");
		user.setPassword("123456");
		user.setRegDate(new Date());
		userService.addUser(user);
		userService.findAllUser();
	}
}
