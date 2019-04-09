package morning.cat.lists;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @describe: {link https://redis.io/commands/#list}
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

    String key = "lists:test1:";

    @Test
    public void test1() {
        // 从头部插入
        jedis.lpush(key + "key", "data0", "data1", "data2", "data3", "data4", "data5");

        // 从尾部插入
        jedis.rpush(key + "key2", "data0", "data1", "data2", "data3", "data4", "data5");

    }

    @Test
    public void test2() {
        // 重置
        jedis.lset(key + "key", 0, "data0000");

        jedis.lset(key + "key2", 0, "data0000");
    }

    @Test
    public void test3() {

        // 从首部弹出
        String node = jedis.lpop(key + "key");
        System.out.println(node);

        // 从尾部弹出
        String node2 = jedis.rpop(key + "key");
        System.out.println(node2);

    }

    @Test
    public void test4() {


    }

    @Test
    public void test5() {

    }

    @Test
    public void test6() {

    }


}
