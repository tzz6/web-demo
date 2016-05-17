package com.tzz.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController  extends BaseController {

	/** 首页 */
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String index() {
		return "/index";
	}
	
	/** 页面缓存 */
	@RequestMapping(value="/cache/pageCache", method = RequestMethod.GET)
	public String pageCache() {
		return "/cache/pageCache";
	}
	
	/** 自定义标签 */
	@RequestMapping(value="/tags", method = RequestMethod.GET)
	public String tags() {
		return "/tags/tags";
	}
}
