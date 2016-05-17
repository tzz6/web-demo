package com.tzz.web.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tzz.rcp.demo.common.QueryResultData;
import com.tzz.web.domain.Role;
import com.tzz.web.domain.User;

/**
 * UserService 接口
 * 
 * @author Administrator
 * 
 */
public interface UserService extends BaseService<User> {

	/** 获取所有的User */
	List<User> findAllUser();
	
	/**添加用户*/
	void addUser(User user);

	User login(String userName, String password);

	Set<Role> queryRoleByUserName(String userName);

	QueryResultData<User> queryUserByPage(int pageNum, int pageSize, Map<String, Object> conditionMap);

	QueryResultData<User> queryAllUser(Map<String, Object> conditionMap);

}
