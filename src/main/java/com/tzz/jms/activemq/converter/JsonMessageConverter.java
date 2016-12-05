package com.tzz.jms.activemq.converter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.util.Assert;

import com.tzz.util.json.JsonMapper;

/**
 * Object <-> Json 消息转换器
 * 
 */
public class JsonMessageConverter implements MessageConverter {

	private static final JsonMapper JSON_MAPPER = JsonMapper.nonDefaultMapper();

	/**
	 * 转换java类型
	 */
	protected Class<?> javaType;

	public Class<?> getJavaType() {
		return javaType;
	}

	/**
	 * 设置 转换java类型
	 */
	public void setJavaType(Class<?> javaType) {
		this.javaType = javaType;
	}

	/**
	 * 转换消息
	 */
	@Override
	public Message toMessage(Object message, Session session) throws JMSException, MessageConversionException {
		// 对象转json
		String json = JSON_MAPPER.toJson(message);

		if (json == null) {
			throw new MessageConversionException("Object[" + message + "] convert to json failed.");
		}
		// 创建消息对象并返回
		return session.createTextMessage(json);
	}

	/**
	 * 消息还原
	 */
	@Override
	public Object fromMessage(Message message) throws JMSException, MessageConversionException {
		// 检查message是否为null
		Assert.notNull(message, "Message is null");
		// 检查message是否TextMessage类型 , 需要获取json字符串.
		Assert.isInstanceOf(TextMessage.class, message, "Message isn't TextMessage");

		String json = ((TextMessage) message).getText();
		// 检查获取的json字符串是否为null, json不可为null.
		Assert.notNull(json, "Message'text is null");

		Object object = javaType == null ? json : JSON_MAPPER.fromJson(json, javaType);

		if (object == null && !"null".equalsIgnoreCase(json)) {
			throw new MessageConversionException("Convert message[" + json + "] to " + javaType.getName() + " failed");
		}
		return object;
	}
}
