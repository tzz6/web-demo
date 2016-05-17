package com.tzz.ws.axis.service.impl;

import com.tzz.ws.axis.service.AxisHelloWorld;

public class AxisHelloWorldImpl implements AxisHelloWorld {

	public String getMessage(String message) {
		return "---------Axis Server-------" + message;
	}
}
