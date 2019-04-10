package morning.cat.datatype.lists;

import morning.cat.datatype.BaseTest;
import org.junit.Test;
import redis.clients.jedis.ListPosition;

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

    /**
     * lset 重置特定位置的值
     */
    @Test
    public void test2() {
        //
        jedis.lset(key + "key", 0, "data0000");

        jedis.lset(key + "key2", 0, "data0000");
    }

    @Test
    public void test3() {

        // 从首部弹出
        String node = jedis.lpop(key + "key2");
        System.out.println(node);

        // 从尾部弹出
        String node2 = jedis.rpop(key + "key2");
        System.out.println(node2);

    }

    /**
     * list 的长度
     */
    @Test
    public void test4() {
        Long length = jedis.llen(key + "key2");
        System.out.println(length);

    }

    /**
     * lindex 获取特定位置的值
     */
    @Test
    public void test5() {
        String indexValue = jedis.lindex(key + "key2", 1);
        System.out.println(indexValue);
    }

    /**
     * linsert 第一个出现data2的位置之后插入333
     */
    @Test
    public void test6() {
        Long indexValue = jedis.linsert(key + "key2", ListPosition.AFTER, "data2", "333");
        System.out.println(indexValue);
    }

    /**
     * lrem  -- 删除 下标4 之后值为 data3 的节点
     */
    @Test
    public void test7() {
        Long indexValue = jedis.lrem(key + "key2", 4L, "data3");
        System.out.println(indexValue);
    }

    /**
     * ltrim 截断
     */
    @Test
    public void test8() {
        String value = jedis.ltrim(key + "key2", 1L, 3L);
        System.out.println(value);
    }

    /**
     * lpushx -- 从首部插入，与 lpush 的区别是，lpush插入的list不存在时会自动进行创建然后插入数据，lpushx 只有当list存在时进行插入操作
     */
    @Test
    public void test9() {
        Long value = jedis.lpushx(key + "key2", "aaa", "bbb", "ccc");
        System.out.println(value);
    }

    @Test
    public void test10() {
        Long value = jedis.rpushx(key + "key2", "qqq", "www", "eee");
        System.out.println(value);
    }

    /**
     * {link http://redisdoc.com/list/rpoplpush.html}
     * 将list1中的尾部数据弹出 插入到list2的首部
     */
    @Test
    public void test11() {
        String rvalue = jedis.rpoplpush(key + "key2", key + "key");
        System.out.println(rvalue);
    }

    @Test
    public void test12() {
        jedis.blpop(key + "key2");
        // ?

    }

}
