package meng.study.jedis.jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisDemo {

	private final String redisHost = "192.168.100.108";
	private final int redisPort = 6379;

	public void test() {
		Jedis jedis = new Jedis(redisHost, redisPort);
		jedis.set("name", "redis");
		String name = jedis.get("name");
		System.out.println(name);
		jedis.close();
	}

	public void testString() {
		Jedis jedis = new Jedis(redisHost, redisPort);

		// 字符串
		jedis.set("name", "redis");
		String name = jedis.get("name");
		System.out.println(name);
		jedis.del("name");
		name = jedis.get("name");
		System.out.println(name);

		// 20 +1+5 -1-7 = 18
		jedis.set("age", "20");
		jedis.incr("age");
		jedis.incrBy("age", 5);
		jedis.decr("age");
		jedis.decrBy("age", 7);
		String age = jedis.get("age");
		System.out.println(age);

		// 字符追加
		jedis.set("address", "AnHui");
		jedis.append("address", " GuoYang");
		String address = jedis.get("address");
		System.out.println(address);

		// 若键不存在,则先赋初始值0，再加1
		jedis.incr("sum");
		String sum = jedis.get("sum");
		System.out.println(sum);

		jedis.close();
	}

	public void testHash() {
		Jedis jedis = new Jedis(redisHost, redisPort);

		// 单一赋值
		jedis.hset("myset", "name", "mengzhang6");
		jedis.hset("myset", "age", "24");
		jedis.hset("myset", "phone", "18365264942");

		// 批量赋值
		Map<String, String> hash = new HashMap<String, String>();
		hash.put("sal", "4000");
		hash.put("address", "AnHui");
		hash.put("id", "20090541");
		jedis.hmset("myset", hash);

		// 获取值
		String myset_name = jedis.hget("myset", "name");
		System.out.println(myset_name);

		// 批量获取
		List<String> vals = jedis.hmget("myset", "age", "id", "name");
		System.out.println(vals);

		// 获取所有
		Map<String, String> vals2 = jedis.hgetAll("myset");
		System.out.println(vals2);

		// 删除
		jedis.hdel("myset", "id", "sal");

		// 是否存在键
		Boolean flag = jedis.hexists("myset", "name");
		System.out.println(flag);
		flag = jedis.hexists("myset", "hexists");
		System.out.println(flag);

		// 键的数量
		Long num = jedis.hlen("myset");
		System.out.println(num);

		// 所有键 所有值
		Set<String> keys = jedis.hkeys("myset");
		List<String> hvals = jedis.hvals("myset");
		System.out.println(keys);
		System.out.println(hvals);

		// 加减
		jedis.hincrBy("myset", "age", 5);
		jedis.hincrBy("myset", "age", -6);// 没有hdecrby

		jedis.close();

	}

	/**
	 * 测试 连接池
	 */
	public void testJedisPool() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(30);// 最大连接数
		config.setMaxIdle(10);// 最大空闲连接数

		// 获取连接池
		JedisPool jedisPool = new JedisPool(config, redisHost, redisPort);
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.set("name", "jedis");
			String name = jedis.get("name");
			System.out.println(name);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null)
				jedis.close();
			if (jedisPool != null)
				jedisPool.close();
		}

	}

}
