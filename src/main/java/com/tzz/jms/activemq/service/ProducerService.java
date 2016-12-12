package com.tzz.jms.activemq.service;

import javax.jms.Destination;

import org.springframework.jms.core.JmsTemplate;

public interface ProducerService {

	public void sendMessage(JmsTemplate jmsTemplate, Destination destination, final Object message);
	
	public void sendMessage(JmsTemplate jmsTemplate, String queueName, final Object message);
}
