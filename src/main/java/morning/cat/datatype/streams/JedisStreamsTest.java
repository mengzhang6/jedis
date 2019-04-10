package morning.cat.datatype.streams;

import morning.cat.datatype.BaseTest;
import org.junit.Test;
import redis.clients.jedis.StreamEntryID;

import java.util.HashMap;
import java.util.Map;

/**
 * redis streams 数据类型是 redis 5.x 新增的数据类型
 * jedis 3.1.0-m1 版本开始支持 streams 类型的操作
 * {link https://redis.io/commands#stream} and {link https://github.com/xetorthio/jedis/releases}
 *
 * @author: morningcat.zhang
 * @date: 2019/4/10 9:40 AM
 */
public class JedisStreamsTest extends BaseTest {

    String key = "streams:test1:";

    @Test
    public void test1() {

        Long time = System.currentTimeMillis() + 3000L;

        Map<String, String> value = new HashMap<>();
        value.put("a", "1");
        value.put("b", "1");
        value.put("c", "1");
        jedis.xadd(key + "key", new StreamEntryID(time, 1000L), value);

        Long xack = jedis.xack(key + "key", "", new StreamEntryID(time, 1000L));
        System.out.println(xack);

        // 这里暂时没搞懂什么意思
        // 需要查看文档后使用
        // https://redis.io/commands/xadd
    }

    @Test
    public void test2() {

    }

}
