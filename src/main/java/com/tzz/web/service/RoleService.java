package com.tzz.web.service;

import java.util.List;

import com.tzz.web.domain.Role;

public interface RoleService extends BaseService<Role> {

	/** 查询全部 */
	List<Role> findAll();

	/** 查询与ids对应的Role */
	List<Role> getByIds(Long[] ids);
	
	/**添加*/
	void addRole(Role role);

}
