package com.tzz.jms.activemq.service.impl;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

import com.tzz.jms.activemq.converter.JsonMessageConverter;
import com.tzz.jms.activemq.service.ProducerService;
import com.tzz.util.LogAppender;

/**
 * 消息生产者--两种模式--模式1：Queue点对点模式、模式2：Topic（发布/订阅）模式
 *
 */
@Service(value = "producerService")
public class ProducerServiceImpl implements ProducerService {

	protected final transient Logger log = Logger.getLogger(LogAppender.JMS);

	/** 模式1.Queue点对点,使用Destination*/
	@Override
	public void sendMessage(JmsTemplate jmsTemplate, Destination destination, Object message) {
		MessageCreator messageCreator = new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				// Message result =
				// session.createTextMessage(message.toString());// 创建消息对象
				// 未配置MessageConverter消息转换器, 使用自定义JsonMessageConverter
				MessageConverter converter = jmsTemplate.getMessageConverter();// 消息转换器
				converter = converter == null ? new JsonMessageConverter() : converter;
				Message result = converter.toMessage(message, session);//// 创建消息对象并返回
				// 获取消息内容
				Object record = message instanceof TextMessage ? ((TextMessage) message).getText() : message;
				String resultStr = record == null ? "null" : record.toString();
				log.info("\r\n发送message:" + resultStr);
				return result;
			}
		};
		jmsTemplate.send(destination, messageCreator);
	}

	/** 模式1.Queue点对点,使用queueName*/
	@Override
	public void sendMessage(JmsTemplate jmsTemplate, String queueName, Object message) {
		MessageCreator messageCreator = new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				// Message result =
				// session.createTextMessage(message.toString());// 创建消息对象
				// 未配置MessageConverter消息转换器, 使用自定义JsonMessageConverter
				MessageConverter converter = jmsTemplate.getMessageConverter();// 消息转换器
				converter = converter == null ? new JsonMessageConverter() : converter;
				Message result = converter.toMessage(message, session);//// 创建消息对象并返回
				// 获取消息内容
				Object record = message instanceof TextMessage ? ((TextMessage) message).getText() : message;
				String resultStr = record == null ? "null" : record.toString();
				log.info("\r\n发送message:" + resultStr);
				return result;
			}
		};
		jmsTemplate.send(queueName, messageCreator);
	}
}
