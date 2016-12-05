package com.tzz.jms.activemq.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.jms.support.converter.MessageConverter;

import com.tzz.util.LogAppender;

/**
 * 监听器1 MessageListener 是最原始的消息监听器，它是JMS规范中定义的一个接口。
 * 其中定义了一个用于处理接收到的消息的onMessage方法，该方法只接收一个Message参数。
 * 我们前面在讲配置消费者的时候用的消息监听器就是MessageListener
 */
public class ConsumerMessageListener implements MessageListener {

	protected final transient Logger log = Logger.getLogger(LogAppender.JMS);
	
	private MessageConverter messageConverter;

	public void setMessageConverter(MessageConverter messageConverter) {
		this.messageConverter = messageConverter;
	}

	public MessageConverter getMessageConverter() {
		return messageConverter;
	}

	public void onMessage(Message message) {
		// 这里我们知道生产者发送的就是一个纯文本消息，所以这里可以直接进行强制转换
		if (message instanceof TextMessage) {
			TextMessage textMsg = (TextMessage) message;
			try {
				Object object = messageConverter.fromMessage(textMsg);
				log.info("\n\t接收到消息：" + object);
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}
}
