package com.tzz.web.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.fasterxml.jackson.databind.ObjectMapper;


public class BaseController {

	/** Date类型数据转换器 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	public void writeJSON(Object status, Object message, HttpServletResponse response) {
		try {
			response.setContentType("text/html; charset=utf-8");

			if (null == status)
				status = "";
			if (null == message)
				message = "";

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", status);
			map.put("message", message);

		    ObjectMapper objectMapper = new ObjectMapper();
//		    JsonGenerator jsonGenerator  = objectMapper.getJsonFactory().createJsonGenerator(System.out, JsonEncoding.UTF8);
//		    jsonGenerator.writeObject(map);  
//		    jsonGenerator.war
			response.getWriter().print(objectMapper.writeValueAsString(map));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}