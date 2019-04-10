package morning.cat.datatype.sets;

import morning.cat.datatype.BaseTest;
import org.junit.Test;

/**
 * 官网文档：{link https://redis.io/commands/#set}
 * 中文文档：{link http://redisdoc.com/set/index.html}
 *
 * @author: morningcat.zhang
 * @date: 2019/4/9 5:04 PM
 */
public class JedisSetsTest extends BaseTest {

    @Test
    public void test1() {
        String key = "sets:test1:";
        Long count = jedis.sadd(key + "key", "val1", "val2", "val3", "val4");
        System.out.println(count);
    }
}
