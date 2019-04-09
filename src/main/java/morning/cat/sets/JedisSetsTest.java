package morning.cat.sets;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @describe: https://redis.io/commands/#set
 * @author: morningcat.zhang
 * @date: 2019/4/9 5:04 PM
 */
public class JedisSetsTest {

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
    public void test1() {
        String key = "sets:test1:";

    }
}
