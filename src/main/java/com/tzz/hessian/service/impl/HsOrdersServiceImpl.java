package com.tzz.hessian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tzz.hessian.service.HsOrdersService;
import com.tzz.web.domain.Orders;
import com.tzz.web.service.OrdersService;

@Service(value = "hsOrdersService")
public class HsOrdersServiceImpl implements HsOrdersService{

	@Autowired
	private OrdersService ordersService;

	public List<Orders> findAllOrders() {
		return ordersService.findAll();
	}

}
