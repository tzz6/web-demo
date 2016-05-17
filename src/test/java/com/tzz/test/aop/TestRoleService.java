package com.tzz.test.aop;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tzz.BaseTest;
import com.tzz.web.domain.Role;
import com.tzz.web.service.RoleService;

public class TestRoleService extends BaseTest{

	@Autowired
	private RoleService roleService;
	
	@Test
	public void testAddRole(){
		Role role = new Role();
		role.setName("测试AOP");
//		roleService.addRole(role);
		roleService.findAll();
	}
}
