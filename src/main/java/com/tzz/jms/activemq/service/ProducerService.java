package com.tzz.jms.activemq.service;

import javax.jms.Destination;

public interface ProducerService {

	public void sendMessage(Destination destination, final Object message);
	
	public void sendMessage(String queueName, final Object message);
	
	/** 模式2.Topic（发布/订阅） */
	public void sendMessageTopic(Destination destination, Object message);
}
