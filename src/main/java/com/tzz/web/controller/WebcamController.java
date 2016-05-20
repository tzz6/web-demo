package com.tzz.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/webcam")
public class WebcamController extends BaseController {

	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
	public void saveFile(HttpServletRequest request, HttpServletResponse response) {
//		String w = request.getParameter("w");
		
		System.out.println("-------------------------uploadImage-------------------------");
		writeJSON("/url", "/url", response);
	}
}
