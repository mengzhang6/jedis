package morning.cat.datatype.lists;

import morning.cat.datatype.BaseTest;
import org.junit.Test;

/**
 * @describe: {link https://redis.io/commands/#list}
 * @author: morningcat.zhang
 * @date: 2019/4/9 5:04 PM
 */
public class JedisListsTest extends BaseTest {

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
