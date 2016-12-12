package com.tzz.jms.activemq.listener.adapter;

/**
 * 监听器3
 *
 */
public class ConsumerListener {

	public void handleMessage(String message) {
		System.out.println("ConsumerListener通过handleMessage接收到消息：" + message);
	}

	public String receiveMessage(String message) {
		System.out.println("ConsumerListener通过receiveMessage接收到消息：" + message);
		return "这是ConsumerListener对象的receiveMessage方法的返回值。";
	}

}