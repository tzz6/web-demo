package com.tzz.test.jms.activemq;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.jms.Destination;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tzz.jms.activemq.service.ProducerService;

/**
 * ActiveMQ测试--服务端（消息生产者）--模式2：Topic（发布/订阅）
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/activemq_topic.xml")
public class ProducerServiceImplTestTopic {

	@Resource(name = "producerService")
	private ProducerService producerService;
	
	/***** 2.Topic（发布/订阅）模式 */
	@Autowired
	@Qualifier("topic_destination_1")
	private Destination topicDestination;
	@Autowired
	@Qualifier("jmsTemplateTopic")
	private JmsTemplate jmsTemplateTopic;
	@Test
	public void testSendTopic() {
		try {
			producerService.sendMessage(jmsTemplateTopic, topicDestination,
					"测试消息发送--" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
