package com.tzz.ws.cxf.service;

import javax.jws.WebService;

@WebService
public interface HelloWorld {
	
	public String getName(String name);
}
