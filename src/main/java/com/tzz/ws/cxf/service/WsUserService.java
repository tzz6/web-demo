package com.tzz.ws.cxf.service;

import java.util.List;

import javax.jws.WebService;

import com.tzz.web.domain.User;

@WebService
public interface WsUserService {

	/** 获取所有的User */
	List<User> findAllUser();
}
