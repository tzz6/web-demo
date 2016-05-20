package com.tzz.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/webcam")
public class WebcamController extends BaseController {

	/** 拍照 */
	@RequestMapping(value = "/photograph", method = RequestMethod.GET)
	public String index(@RequestParam(value = "id", required = false) String id, HttpServletRequest request,
			ModelMap model) {
		System.out.println("-------------------------------id:" + id);
		return "/webcam/photograph";
	}
	
	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
	public void saveFile(HttpServletRequest request, HttpServletResponse response) {
//		String w = request.getParameter("w");
		
		System.out.println("-------------------------uploadImage-------------------------");
		writeJSON("/url", "/url", response);
	}
}
