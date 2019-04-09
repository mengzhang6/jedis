package morning.cat.pub;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @describe: 类描述信息
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


    @Test
    public void test1() {


    }
}
