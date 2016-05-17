package com.tzz.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tzz.web.dao.BaseDao;
import com.tzz.web.domain.Role;
import com.tzz.web.service.RoleService;

@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

	@Override
	@Resource(name = "roleDao")
	public void setBaseDao(BaseDao<Role> baseDao) {
		super.setBaseDao(baseDao);
	}

	/** 查询与ids对应的Role */
	public List<Role> getByIds(Long[] roleIds) {
		return findByIds(roleIds);
	}
	
	/**添加*/
	public void addRole(Role role) {
		saveEntity(role);
	}
	
}
