package com.tzz.test;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDateSource {
	
	private ApplicationContext ac;
	
	@Before
	public void InitBinder(){
		ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	}

	@Test
	public void testGetDateSource() {
		DataSource dataSource = (DataSource) ac.getBean("dataSource");
		SessionFactory sessionFactory = (SessionFactory) ac.getBean("sessionFactory");
		System.out.println("------------------------------------");
		System.out.println(dataSource);
		System.out.println(sessionFactory);
	}
}
