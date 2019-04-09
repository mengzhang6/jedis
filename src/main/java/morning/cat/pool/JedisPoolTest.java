package morning.cat.pool;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2019/4/9 5:04 PM
 */
public class JedisPoolTest {

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
