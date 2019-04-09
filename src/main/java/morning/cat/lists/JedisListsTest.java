package morning.cat.lists;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @describe: https://redis.io/commands/#list
 * @author: morningcat.zhang
 * @date: 2019/4/9 5:04 PM
 */
public class JedisListsTest {

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
        String key = "lists:test1:";

    }

    @Test
    public void test2(){

    }

    @Test
    public void test3(){


    }

    @Test
    public void test4(){

    }

    @Test
    public void test5(){

    }

    @Test
    public void test6(){

    }


}
