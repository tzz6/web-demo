package com.tzz.web.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tzz.web.dao.BaseDao;
import com.tzz.web.domain.Orders;
import com.tzz.web.service.OrdersService;

@Service("ordersService")
public class OrdersServiceImpl extends BaseServiceImpl<Orders> implements OrdersService {

	@Override
	@Resource(name = "ordersDao")
	public void setBaseDao(BaseDao<Orders> baseDao) {
		super.setBaseDao(baseDao);
	}

	/** 单值检索 */
	public Object uniqueResult(String hql, Serializable... serializables) {
		return uniqueResult(hql, serializables);
	}

}
