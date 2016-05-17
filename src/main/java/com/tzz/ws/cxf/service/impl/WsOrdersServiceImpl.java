package com.tzz.ws.cxf.service.impl;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzz.web.domain.Orders;
import com.tzz.web.service.OrdersService;
import com.tzz.ws.cxf.service.WsOrdersService;

@WebService(endpointInterface = "com.tzz.ws.cxf.service.WsOrdersService", serviceName = "wsOrdersServiceImpl")
public class WsOrdersServiceImpl implements WsOrdersService{

	@Autowired
	private OrdersService ordersService;

	public List<Orders> findAllOrders() {
		return ordersService.findAll();
	}

}
