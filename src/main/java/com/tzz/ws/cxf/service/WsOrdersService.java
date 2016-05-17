package com.tzz.ws.cxf.service;

import java.util.List;

import javax.jws.WebService;

import com.tzz.web.domain.Orders;

@WebService
public interface WsOrdersService {

	List<Orders> findAllOrders();
}
