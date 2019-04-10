package morning.cat.datatype.hashes;

import morning.cat.datatype.BaseTest;
import org.junit.Test;
import redis.clients.jedis.ScanResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @describe: {link https://redis.io/commands/#hash}
 * @author: morningcat.zhang
 * @date: 2019/4/9 5:03 PM
 */
public class JedisHashesTest extends BaseTest {

    /**
     * hset 赋值
     * hmset 批量赋值
     */
    @Test
    public void testHash() {

        String key = "hashes:test1:";

        // 单一赋值
        jedis.hset(key + "myset", "name", "moeningcat");
        jedis.hset(key + "myset", "age", "26");
        jedis.hset(key + "myset", "phone", "18365264942");

        // 批量赋值
        Map<String, String> hash = new HashMap<String, String>();
        hash.put("sal", "4000");
        hash.put("address", "AnHui");
        hash.put("id", "20090541");
        jedis.hmset(key + "myset", hash);
    }

    /**
     * hget
     * hmget
     * hgetAll
     */
    @Test
    public void testHash2() {
        String key = "hashes:test1:";

        // 获取值
        String myset_name = jedis.hget(key + "myset", "name");
        System.out.println(myset_name);

        // 批量获取
        List<String> vals = jedis.hmget(key + "myset", "age", "id", "name");
        System.out.println(vals);

        // 获取所有
        Map<String, String> vals2 = jedis.hgetAll(key + "myset");
        System.out.println(vals2);
    }

    /**
     * hdel
     * hexists
     */
    @Test
    public void testHash3() {

        String key = "hashes:test1:";

        // 删除
        jedis.hdel(key + "myset", "id", "sal");

        // 是否存在键
        Boolean flag = jedis.hexists(key + "myset", "name");
        System.out.println(flag);

        flag = jedis.hexists(key + "myset", "notExistKey");
        System.out.println(flag);
    }

    /**
     * hlen
     */
    @Test
    public void testHash4() {

        String key = "hashes:test1:";

        // 键的数量
        Long num = jedis.hlen(key + "myset");
        System.out.println(num);
    }

    /**
     * hkeys
     * hvals
     */
    @Test
    public void testHash5() {
        String key = "hashes:test1:";

        // 所有键 所有值
        Set<String> keys = jedis.hkeys(key + "myset");
        List<String> hvals = jedis.hvals(key + "myset");
        System.out.println(keys);
        System.out.println(hvals);
    }

    /**
     * hincrBy
     */
    @Test
    public void testHash6() {
        String key = "hashes:test1:";

        // 加减
        jedis.hincrBy(key + "myset", "age", 10);
        // 没有hdecrby
        jedis.hincrBy(key + "myset", "age", -6);

    }


    @Test
    public void testHash7() {
        String key = "hashes:test1:";
        Long hstrlen = jedis.hstrlen(key + "myset", "address");
        System.out.println(hstrlen);
    }

    @Test
    public void testHash8() {
        String key = "hashes:test1:";

        Long hsetnx = jedis.hsetnx(key + "myset", "address", "ssss");
        System.out.println(hsetnx);
    }

    @Test
    public void testHash9() {
        String key = "hashes:test1:";

        ScanResult<Map.Entry<String, String>> scanResult = jedis.hscan(key + "myset", "0");
        System.out.println(scanResult);
        System.out.println(scanResult.getResult());
    }
}
