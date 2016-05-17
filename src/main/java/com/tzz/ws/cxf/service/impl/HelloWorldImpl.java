package com.tzz.ws.cxf.service.impl;

import javax.jws.WebService;

import com.tzz.ws.cxf.service.HelloWorld;

@WebService(endpointInterface = "com.tzz.ws.cxf.service.HelloWorld", serviceName = "helloWorldImpl")
public class HelloWorldImpl implements HelloWorld {

	public String getName(String name) {
		System.out.println("Server-------"+name);
		return name;
	}
}
