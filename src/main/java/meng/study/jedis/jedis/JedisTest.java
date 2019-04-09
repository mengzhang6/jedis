package meng.study.jedis.jedis;

import java.util.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisTest {

    private final static String redisHost = "localhost";
    private final static int redisPort = 6379;
    static Jedis jedis = null;

    @BeforeClass
    public static void beforeClass() {
        if (jedis == null) {
            jedis = new Jedis(redisHost, redisPort);
        }
    }

    @AfterClass
    public static void afterClass() {
        if (jedis != null) {
            jedis.close();
        }
    }

    @Test
    public void testString() {
        // 字符串
        jedis.set("name", "redis5.0.4");
        String name = jedis.get("name");
        System.out.println(name);

        jedis.del("name");
        System.out.println(Objects.isNull(jedis.get("name")));
    }

    @Test
    public void testString2() {
        // 20 +1+5 -1-7 = 18
        jedis.set("age", "20");
        System.out.println(jedis.get("age"));

        // +1
        jedis.incr("age");
        // +5
        jedis.incrBy("age", 5);

        System.out.println(jedis.get("age"));

        // -1
        jedis.decr("age");
        // -7
        jedis.decrBy("age", 7);

        System.out.println(jedis.get("age"));

        // print
        String age = jedis.get("age");
        System.out.println(age);
    }

    @Test
    public void testString3() {
        // 字符追加
        jedis.set("key", "I Love ");
        jedis.append("key", "Gouzi");
        String vlaue = jedis.get("key");
        System.out.println(vlaue);
    }

    @Test
    public void testString4() {
        // 若键不存在,则先赋初始值0，再加1
        jedis.incr("sum");
        String sum = jedis.get("sum");
        System.out.println(sum);
    }

    @Test
    public void testHash() {

        // 单一赋值
        jedis.hset("myset", "name", "moeningcat");
        jedis.hset("myset", "age", "26");
        jedis.hset("myset", "phone", "18365264942");

        // 批量赋值
        Map<String, String> hash = new HashMap<String, String>();
        hash.put("sal", "4000");
        hash.put("address", "AnHui");
        hash.put("id", "20090541");
        jedis.hmset("myset", hash);
    }

    @Test
    public void testHash2() {
        // 获取值
        String myset_name = jedis.hget("myset", "name");
        System.out.println(myset_name);

        // 批量获取
        List<String> vals = jedis.hmget("myset", "age", "id", "name");
        System.out.println(vals);

        // 获取所有
        Map<String, String> vals2 = jedis.hgetAll("myset");
        System.out.println(vals2);
    }

    @Test
    public void testHash3() {
        // 删除
        jedis.hdel("myset", "id", "sal");

        // 是否存在键
        Boolean flag = jedis.hexists("myset", "name");
        System.out.println(flag);

        flag = jedis.hexists("myset", "notExistKey");
        System.out.println(flag);
    }

    @Test
    public void testHash4() {
        // 键的数量
        Long num = jedis.hlen("myset");
        System.out.println(num);
    }

    @Test
    public void testHash5() {
        // 所有键 所有值
        Set<String> keys = jedis.hkeys("myset");
        List<String> hvals = jedis.hvals("myset");
        System.out.println(keys);
        System.out.println(hvals);
    }

    @Test
    public void testHash6() {

        // 加减
        jedis.hincrBy("myset", "age", 10);
        // 没有hdecrby
        jedis.hincrBy("myset", "age", -6);

    }

    /**
     * 测试 连接池
     */
    @Test
    public void testJedisPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        // 最大连接数
        config.setMaxTotal(30);
        // 最大空闲连接数
        config.setMaxIdle(10);

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
            if (jedis != null) {
                jedis.close();
            }
            if (jedisPool != null) {
                jedisPool.close();
            }
        }

    }

}
