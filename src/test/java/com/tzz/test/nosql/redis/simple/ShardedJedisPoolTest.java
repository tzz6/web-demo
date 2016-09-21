package com.tzz.test.nosql.redis.simple;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class ShardedJedisPoolTest {

	private static ShardedJedisPool shardedJedisPool;

	/** 初始化jedis连接池 */
	@Before
	public void initShardedJedisPool() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		// 读取配置文件
		ResourceBundle bundle = ResourceBundle.getBundle("redis_config");
		jedisPoolConfig.setMaxTotal(Integer.parseInt(bundle.getString("redis.pool.maxActive")));
		jedisPoolConfig.setMaxIdle(Integer.parseInt(bundle.getString("redis.pool.maxIdle")));
		jedisPoolConfig.setMaxWaitMillis(Long.parseLong(bundle.getString("redis.pool.maxWait")));
		jedisPoolConfig.setTestOnBorrow(Boolean.parseBoolean(bundle.getString("redis.pool.testOnBorrow")));
		jedisPoolConfig.setTestOnReturn(Boolean.parseBoolean(bundle.getString("redis.pool.testOnReturn")));
		String host = bundle.getString("redis.host.1");
		String host2 = bundle.getString("redis.host.2");
		int port = Integer.valueOf(bundle.getString("redis.port.1"));
		int port2 = Integer.valueOf(bundle.getString("redis.port.2"));
		int timeout = Integer.valueOf(bundle.getString("redis.pool.maxWait"));
		String password = bundle.getString("redis.password");

		// 构造连接池
		JedisShardInfo jedisShardInfo1 = new JedisShardInfo(host, port, timeout);
		jedisShardInfo1.setPassword(password);
		JedisShardInfo jedisShardInfo2 = new JedisShardInfo(host2, port2, timeout);
		jedisShardInfo2.setPassword(password);
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		shards.add(jedisShardInfo1);
		shards.add(jedisShardInfo2);
		shardedJedisPool = new ShardedJedisPool(jedisPoolConfig, shards);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testSet() {
		ShardedJedis jedis = null;
		try {
			// 从连接池中获取jedis实例
			jedis = shardedJedisPool.getResource();
			String key = "Test_Poolxx4";
			jedis.del(key);
			jedis.set(key, "Test_Pool");
		} catch (Exception e) {
			// 销毁对象
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			// 释放对象池
			shardedJedisPool.returnResource(jedis);
		}
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testGet() {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			System.out.println(jedis.get("Test_Poolxx4"));
		} catch (Exception e) {
			// 销毁对象
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			// 释放对象池
			shardedJedisPool.returnResource(jedis);
		}
	}
}
