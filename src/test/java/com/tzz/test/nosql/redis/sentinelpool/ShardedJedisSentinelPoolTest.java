package com.tzz.test.nosql.redis.sentinelpool;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tzz.nosql.redis.sentinelpool.ShardedJedisSentinelPool;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class ShardedJedisSentinelPoolTest {

	@Test
	public void testX() {
		try {
			ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext_redis.xml");
			ShardedJedisSentinelPool pool = (ShardedJedisSentinelPool) ac.getBean("shardedJedisPool");

			ShardedJedis jedis = null;
			for (int i = 0; i < 1; i++) {
				try {
					jedis = pool.getResource();
					jedis.set("KEY: " + i, "" + i);
					System.out.print(i);
					System.out.print(" ");
					Thread.sleep(100);
					pool.returnResource(jedis);
				} catch (JedisConnectionException e) {
					System.out.print("x");
					i--;
					Thread.sleep(1000);
				}
			}
			System.out.println("---------------------");
			for (int i = 0; i < 100; i++) {
				try {
					jedis = pool.getResource();
					System.out.print(".");
					Thread.sleep(100);
					pool.returnResource(jedis);
				} catch (JedisConnectionException e) {
					System.out.print("x");
					i--;
					Thread.sleep(1000);
				}
			}
			pool.destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
