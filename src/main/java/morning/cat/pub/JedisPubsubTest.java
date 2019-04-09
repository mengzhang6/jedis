package morning.cat.pub;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.JedisSentinelPool;

/**
 * @describe: {link https://redis.io/commands/#pubsub}
 * @author: morningcat.zhang
 * @date: 2019/4/9 5:04 PM
 */
public class JedisPubsubTest {

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

    private static final String channel = "JedisPubsubTest";


    @Test
    public void test1() {
        Long success = jedis.publish(channel, "消息" + System.currentTimeMillis());
        System.out.println(success);

    }

    @Test
    public void test2() {
        // 监听
        JedisPubSub jedisPubSub = new MyJedisPubSub();
        jedis.subscribe(jedisPubSub, channel);
    }

    @Test
    public void test3() {
        // 监听 支持正则表达式
        JedisPubSub jedisPubSub = new MyJedisPubSub();
        jedis.psubscribe(jedisPubSub, "JedisPub*Test");
    }

    @Test
    public void test4() {


    }

}
