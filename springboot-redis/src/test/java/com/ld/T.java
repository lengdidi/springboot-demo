package com.ld;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ld.entity.Users;

import redis.clients.jedis.Jedis;

/**
 * @author Administrator
 *
 */
public class T {
	// 创建一个Jedis对象
	Jedis jedis = new Jedis("192.168.6.44", 6379);

	/**
	 * 测试插入string和获取string
	 */
	@Test
	public void testString() {
		jedis.set("k1", "v1");
		String string = jedis.get("k1");
	}

	/**
	 * 测试插入多个string和获取多个值
	 */
	@Test
	public void testMoreString() {
		jedis.mset("k2", "v2", "k3", "v3");
		List<String> mget = jedis.mget("k1", "k2", "k3");
		System.out.println(mget);
	}

	/**
	 * 测试插入string设置过期时间
	 */
	@Test
	public void testExpireAndttl() {
		Long expire = jedis.expire("k1", 10);
		while (expire != -2) {
			expire = jedis.ttl("k1");
			System.out.println("剩余过期时间为" + expire);
		}
	}

	/**
	 * 测试插入List和获取List
	 */
	@Test
	public void testList() {
//		jedis.lpush("list01", "1","2","3","4","5");
		jedis.lpush("list02", "1", "2", "3", "4", "5");
		List<String> lrange = jedis.lrange("list02", 0, -1);
		System.out.println(lrange);
	}

	/**
	 * 测试插入Set和获取Set
	 */
	@Test
	public void testSet() {
		jedis.sadd("s1", "s001", "s002", "s003");
		Set<String> smembers = jedis.smembers("s1");
		System.out.println(smembers);
	}

	/**
	 * 测试插入ZSet集合和获取ZSet集合
	 */
	@Test
	public void testZSet() {
		Map<String, Double> scoreMembers = new HashMap<String, Double>();
		scoreMembers.put("zs", 70.0);
		scoreMembers.put("ls", 80.0);
		scoreMembers.put("ww", 90.0);
		scoreMembers.put("legend", 100.0);
		jedis.zadd("zs1", scoreMembers);
		Set<String> zrange = jedis.zrange("zs1", 0, -1);
		System.out.println(zrange);
	}

	/**
	 * 测试插入hash和获取hash
	 */
	@Test
	public void testHash() {
//		Map<String, String> hash = new HashMap<String, String>();
//		hash.put("name", "zhangsan");
//		hash.put("score", "80");
//		hash.put("age", "18");
//		jedis.hset("h1", hash);
		String value = jedis.hget("h1", "age");
		List<String> hmget = jedis.hmget("h1", "age", "score", "name");
		int parseInt = Integer.parseInt(hmget.get(0));
		System.out.println(value);
		System.out.println(hmget);
		System.out.println(parseInt);
	}

	/**
	 * 测试插入多个hash和获取多个hash
	 */
	@Test
	public void testMoreHash() {
		jedis.select(2);
		Map<String, String> hash = new HashMap<String, String>();
		hash.put("name", "wangwu");
		hash.put("score", "90");
		hash.put("age", "22");
		jedis.hmset("h2", hash);
		List<String> hmget = jedis.hmget("h2", "name", "age");
		System.out.println(hmget);
	}

	/**
	 * 测试插入JSON格式和获取JSON转换成对象
	 */
	@Test
	public void testObject() {
		Users users = new Users();
		users.setId(18);
		users.setUserPassword("123456");
		users.setUserName("zhangsan");
		String jsonString = JSONObject.toJSONString(users);
		jedis.select(1);
		String key = "user";
		jedis.set(key, jsonString);
		String string = jedis.get(key);
		System.out.println(string);
		Users parseObject = JSON.parseObject(string, Users.class);
		System.out.println(parseObject.getUserPassword());
		System.out.println(parseObject);
	}

}
