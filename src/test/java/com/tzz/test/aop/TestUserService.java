package com.tzz.test.aop;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tzz.BaseTest;
import com.tzz.rcp.demo.common.QueryResultData;
import com.tzz.web.domain.Role;
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
	
	@Test
	public void testLogin(){
		try {
			User user = userService.login("测试02", "123456");
			System.out.println(user.getName()+"--"+user.getPassword());
			Set<Role> roles = user.getRoles();
			for (Role role : roles) {
				System.out.println(role.getName());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQueryUserByPage(){
		try {
			Map<String, Object> conditionMap = new HashMap<String, Object>();
			conditionMap.put("username", "测试02");
			conditionMap.put("sex", "男");
			conditionMap.put("startDate", "2016-01-05 15:37:45");
			conditionMap.put("endDate", "2016-01-05 15:37:45");
			QueryResultData<User> result = userService.queryUserByPage(1, 3, conditionMap);
			List<User> users = result.getResult();
			for (User user : users) {
				System.out.println(user.getName()+"--"+user.getPassword());
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}