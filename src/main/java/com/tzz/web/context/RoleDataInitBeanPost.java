package com.tzz.web.context;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.tzz.web.domain.Department;
import com.tzz.web.service.DepartmentService;
import com.tzz.web.service.impl.DepartmentServiceImpl;
/**
 * 初始化角色表
 *
 */
public class RoleDataInitBeanPost implements BeanPostProcessor {

	private DepartmentService departmentService;
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	public DepartmentService getDepartmentService() {
		return departmentService;
	}
	
	public Object postProcessAfterInitialization(Object object, String str) throws BeansException {
		if(object instanceof DepartmentServiceImpl){
			List<Department> list = departmentService.findAll();
			if(list == null || list.size() == 0){
				System.out.println("**********************init***********************"+ object.getClass().toString());
				Department department = new Department();
				department.setName("董事会");
				departmentService.saveDepartment(department);
			}
		}
		return object;
	}

	public Object postProcessBeforeInitialization(Object object, String str) throws BeansException {
		if(object instanceof DepartmentServiceImpl){
			System.out.println("**********************Finish***********************");
		}
		return object;
	}

}
