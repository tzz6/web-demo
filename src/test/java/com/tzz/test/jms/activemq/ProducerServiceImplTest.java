package com.tzz.test.jms.activemq;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
 * ActiveMQ测试--服务端（消息生产者）--模式1:Queue点对点--监听器1
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
/** 测试监听器1 */
//@ContextConfiguration(value = { "/jms/activemq_queue.xml", "/jms/activemq_queue_listener_1.xml" })
/** 测试监听器2 */
//@ContextConfiguration(value = { "/jms/activemq_queue.xml", "/jms/activemq_queue_listener_2.xml" })
/** 测试监听器3 */
@ContextConfiguration(value = { "/jms/activemq_queue.xml", "/jms/activemq_queue_listener_4.xml" })
public class ProducerServiceImplTest {

	@Resource(name = "producerService")
	private ProducerService producerService;
	
	/***** 模式1:Queue点对点 */
	@Autowired
	@Qualifier("destination_1")
	private Destination destination;
	@Autowired
	@Qualifier("jmsTemplate")
	private JmsTemplate jmsTemplate;

	/***** 1****测试参数为Destination的sendMessage方法 **********/
	@Test
	public void testSend() {
		producerService.sendMessage(jmsTemplate, destination,
				"测试消息发送--" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
	}

	@Test
	public void testSendBean() {
		UserBean userBean = new UserBean();
		userBean.setId(1);
		userBean.setUserName("Test001");
		producerService.sendMessage(jmsTemplate, destination, userBean);
	}

	@Test
	public void testSendList() {
		UserBean userBean1 = new UserBean();
		userBean1.setId(1);
		userBean1.setUserName("Test001");
		UserBean userBean2 = new UserBean();
		userBean2.setId(2);
		userBean2.setUserName("测试002");
		List<UserBean> userBeans = new ArrayList<UserBean>();
		userBeans.add(userBean1);
		userBeans.add(userBean2);
		producerService.sendMessage(jmsTemplate, destination, userBeans);
	}
	
	/***** 2****测试参数为queueName的sendMessage方法 **********/
	@Test
	public void testSendQueueName() {
		String queueName = "queue_1";
		producerService.sendMessage(jmsTemplate, queueName,
				"测试消息发送--参数为queueName--" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
	}
}
