package morning.cat.datatype;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import redis.clients.jedis.Jedis;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2019/4/10 9:41 AM
 */
public class BaseTest {

    private final static String redisHost = "localhost";
    private final static int redisPort = 6379;
    protected static Jedis jedis = null;

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

}
