package com.tzz.web.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.tzz.web.domain.User;
import com.tzz.web.service.UserService;

@Controller
@RequestMapping("/freemarker")
public class FreemarkerController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	/** 用户信息列表 */
	@RequestMapping("/list")
	public String list(ModelMap model) {
		// 获取有的User
		List<User> users = userService.findAllUser();
		model.put("users", users);
		return "/freemarker/user_list";
	}
}