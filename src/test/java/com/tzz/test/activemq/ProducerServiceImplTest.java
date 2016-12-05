package com.tzz.test.activemq;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tzz.jms.activemq.service.ProducerService;

/**
 * ActiveMQ测试
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext_activemq.xml")
public class ProducerServiceImplTest {

	@Resource(name = "producerService")
	private ProducerService producerService;
	/** 模式1.Queue点对点 */
	@Autowired
	@Qualifier("destination_1")
	private Destination destination;

	/** 模式2.Topic（发布/订阅） */
	@Autowired
	@Qualifier("topic_destination_1")
	private Destination topicDestination;

	/*********************************************************************************/
	/******************************** 模式1.Queue点对点 ********************************/
	/*********************************************************************************/
	/***** 1****测试参数为Destination的sendMessage方法 **********/
	@Test
	public void testSend() {
		producerService.sendMessage(destination,
				"测试消息发送--" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
	}

	@Test
	public void testSendBean() {
		UserBean userBean = new UserBean();
		userBean.setId(1);
		userBean.setUserName("Test001");
		producerService.sendMessage(destination, userBean);
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
		producerService.sendMessage(destination, userBeans);
	}

	/***** 2****测试参数为queueName的sendMessage方法 **********/
	@Test
	public void testSendQueueName() {
		String queueName = "queue_1";
		producerService.sendMessage(queueName,
				"测试消息发送--" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
	}

	/*********************************************************************************/
	/******************************** 模式2.Topic（发布/订阅） ********************************/
	/*********************************************************************************/
	@Test
	public void testSendMessageTopic() {
		for (int i = 0; i < 5; i++) {
			producerService.sendMessageTopic(topicDestination,
					"测试消息发送---" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
		}
	}

}
