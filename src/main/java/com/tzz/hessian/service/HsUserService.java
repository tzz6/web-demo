package com.tzz.hessian.service;


import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tzz.rcp.demo.common.QueryResultData;
import com.tzz.web.domain.Department;
import com.tzz.web.domain.Role;
import com.tzz.web.domain.User;

public interface HsUserService {

	User login(String userName, String password);
	
	Set<Role> queryRoleByUserName(String userName);
	
	QueryResultData<User> queryUserByPage(int pageNum, int pageSize, Map<String, Object> conditionMap);
	
	QueryResultData<User> queryAllUser(Map<String, Object> conditionMap);
	
	List<Role> findAllRole();
	
	List<Department> findAllDepartment();
	
	void saveUser(String username, String password, String regDateStr, String sex, String departmentId, String roles);
	
	User deleteUser(User user);
}
