package com.tzz.ws.cxf.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzz.web.domain.User;
import com.tzz.web.service.UserService;
import com.tzz.ws.cxf.service.WsUserService;
@WebService(endpointInterface = "com.tzz.ws.cxf.service.WsUserService", serviceName = "wsUserServiceImpl")
public class WsUserServiceImpl implements WsUserService{

	@Autowired
	private UserService userService;
	
	public List<User> findAllUser() {
		List<User> users = new ArrayList<User>();
//		Department department = new Department();
//		department.setId(1);
//		department.setName("部门1");
//		User user = new User();
//		user.setId(1);
//		user.setName("a");
//		user.setDepartment(department);
//		Role role = new Role();
//		role.setId(1);
//		role.setName("角色1");
//		user.getRoles().add(role);
//		users.add(user);
		List<User> usersList =  userService.findAllUser();
		for (User user : usersList) {
			user.getDepartment();
		}
		return users;
	}

}
