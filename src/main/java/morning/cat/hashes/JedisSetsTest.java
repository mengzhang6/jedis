package morning.cat.hashes;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2019/4/9 5:03 PM
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
}
