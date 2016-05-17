package com.tzz.web.controller;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tzz.web.domain.Department;
import com.tzz.web.service.DepartmentService;
import com.tzz.web.service.UserService;

@Controller
@RequestMapping("/department")
public class DepartmentController extends BaseController {

	private DepartmentService departmentService;

	/** 注入departmentService */
	@Resource(name = "departmentService")
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@Resource(name = "userService")
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/** 到达部门添加页面 */
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public ModelAndView toAddDepartment() {
		ModelAndView model = new ModelAndView();
		// 获取部门
		List<Department> parents = departmentService.findAll();
		model.addObject("parents", parents);
		model.setViewName("/department/addDepartment");
		return model;
	}

	/** 到达部门添加页面 */
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String save(@RequestParam(value="parentId",required=false) Long parentId, Department department) {
		if(parentId != null){
			// 获取上一级部门
			Department parent = departmentService.findDepartment(parentId);
			department.setParent(parent);
		}
		departmentService.saveDepartment(department);
		return "redirect:/department/list";
	}

	/** 部门信息列表 */
	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView model = new ModelAndView();
		List<Department> departments = departmentService.findAll();
		model.setViewName("/department/departmentList");
		model.addObject("departments", departments);
		return model;
	}

	/** 部门信息删除 */
	@RequestMapping("/delete/{dId}")
	public String delete(@PathVariable("dId") Long dId) {
		Department d = departmentService.getEntity(dId);
		delete(d);
		return "redirect:/department/list";
	}
	
	private void delete(Department d){
		Set<Department> sets = d.getChild();
		for (Department department : sets) {
			delete(department);
		}
		departmentService.deleteEntity(d);
	}
}
