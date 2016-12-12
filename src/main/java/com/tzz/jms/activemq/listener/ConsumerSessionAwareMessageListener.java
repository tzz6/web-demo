package com.tzz.jms.activemq.listener;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.jms.support.converter.MessageConverter;

import com.tzz.util.LogAppender;

/**
 * 监听器2 SessionAwareMessageListener是Spring为我们提供的，它不是标准的JMS
 * MessageListener。MessageListener的设计只是纯粹用来接收消息的，
 * 假如我们在使用MessageListener处理接收到的消息时我们需要发送一个消息
 * 通知对方我们已经收到这个消息了，那么这个时候我们就需要在代码里面去重新获取一个Connection或Session。
 * SessionAwareMessageListener的设计就是为了方便我们在接收到消息后发送一个回复的消息，
 * 它同样为我们提供了一个处理接收到的消息的onMessage方法， 但是这个方法可以同时接收两个参数，一个是表示当前接收到的消息Message，
 * 另一个就是可以用来发送消息的Session对象
 */
public class ConsumerSessionAwareMessageListener implements SessionAwareMessageListener<Message> {

	protected final transient Logger log = Logger.getLogger(LogAppender.JMS);

	//消息转换器
	private MessageConverter messageConverter;
	
	private Destination destination; 

	public void setMessageConverter(MessageConverter messageConverter) {
		this.messageConverter = messageConverter;
	}

	public MessageConverter getMessageConverter() {
		return messageConverter;
	}

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	@Override
	public void onMessage(Message message, Session session) throws JMSException {
		if (message instanceof TextMessage) {
			TextMessage textMsg = (TextMessage) message;
			Object object = messageConverter.fromMessage(textMsg);
			log.info("\n\t接收到message：" + object + "....监听器2 SessionAwareMessageListener");
			//发送消息  
			MessageProducer producer = session.createProducer(destination);  
			Message m = session.createTextMessage("SessionAwareMessageListener........接收成功");  
			producer.send(m);  
		}
		
	}
}
