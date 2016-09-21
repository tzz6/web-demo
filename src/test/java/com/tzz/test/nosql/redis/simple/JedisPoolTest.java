package com.tzz.test.nosql.redis.simple;

import java.util.ResourceBundle;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolTest {

	private static JedisPool jedisPool;

	/** 初始化jedis连接池 */
	@Before
	public void initJedisPool() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		// 读取配置文件
		ResourceBundle bundle = ResourceBundle.getBundle("redis_config");
		jedisPoolConfig.setMaxTotal(Integer.parseInt(bundle.getString("redis.pool.maxActive")));
		jedisPoolConfig.setMaxIdle(Integer.parseInt(bundle.getString("redis.pool.maxIdle")));
		jedisPoolConfig.setMaxWaitMillis(Long.parseLong(bundle.getString("redis.pool.maxWait")));
		jedisPoolConfig.setTestOnBorrow(Boolean.parseBoolean(bundle.getString("redis.pool.testOnBorrow")));
		jedisPoolConfig.setTestOnReturn(Boolean.parseBoolean(bundle.getString("redis.pool.testOnReturn")));
		String host = bundle.getString("redis.host.1");
		int port = Integer.valueOf(bundle.getString("redis.port.1"));
		int timeout = Integer.valueOf(bundle.getString("redis.pool.maxWait"));
		String password = bundle.getString("redis.password");

		// 构造连接池
		jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testSet() {
		Jedis jedis = null;
		try {
			// 从连接池中获取jedis实例
			jedis = jedisPool.getResource();
			String key = "Test_Pool";
			jedis.del(key);
			jedis.set(key, "Test_Pool");
		} catch (Exception e) {
			// 销毁对象
			jedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			// 释放对象池
			jedisPool.returnResource(jedis);
		}
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testGet() {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			System.out.println(jedis.get("Test_Pool"));
		} catch (Exception e) {
			// 销毁对象
			jedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			// 释放对象池
			jedisPool.returnResource(jedis);
		}
	}
}