package morning.cat.strings;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Objects;

/**
 * https://redis.io/commands/#string
 */
public class JedisStringsTest {

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

    /**
     * set
     * get
     */
    @Test
    public void testString() {
        // 字符串
        jedis.set("strings:name", "redis5.0.4");
        String name = jedis.get("name");
        System.out.println(name);

        jedis.del("name");
        System.out.println(Objects.isNull(jedis.get("name")));
    }

    /**
     * incr
     * incrBy
     * decr
     * decrBy
     */
    @Test
    public void testString2() {
        String key = "strings:testString2:";

        jedis.set(key + "age", "20");
        System.out.println(jedis.get(key + "age"));

        // +1
        jedis.incr(key + "age");
        // +5
        jedis.incrBy(key + "age", 5);

        System.out.println(jedis.get(key + "age"));

        // -1
        jedis.decr(key + "age");
        // -7
        jedis.decrBy(key + "age", 7);

        System.out.println(jedis.get(key + "age"));

        // print
        String age = jedis.get(key + "age");
        System.out.println(age);

        // 若键不存在,则先赋初始值0，再加1
        jedis.incr(key + "sum");
        String sum = jedis.get(key + "sum");
        System.out.println(sum);
    }

    /**
     * append 字符追加
     */
    @Test
    public void testString3() {
        String key = "strings:testString3:";

        jedis.set(key + "name", "how are you ");
        jedis.append(key + "name", "Gouzi");
        String vlaue = jedis.get(key + "name");
        System.out.println(vlaue);
    }

    /**
     * setnx 赋值 key不存在时
     * setex 赋值 设置超时时间
     * psetex 赋值 设置超时时间
     * <p>
     * mset 批量操作
     * msetnx 批量操作
     */
    @Test
    public void testString4() {
        String key = "strings:testString4:";

        // 赋值
        String setResult = jedis.set(key + "name", "gouzi");
        System.out.println(setResult);

        // 若key不存在则进行set
        // SET if not eXists
        long setResult2 = jedis.setnx(key + "name", "gouzi");
        System.out.println(setResult2);

        // 赋值 同时设置超时时间（单位：秒）
        String setResult3 = jedis.setex(key + "setex", 30, "gouzi");
        System.out.println(setResult3);

        // 批量赋值
        String msetResult = jedis.mset(key + "id1", "value1", key + "id2", "value2", key + "id3", "value3");
        System.out.println(msetResult);

        Long msetnx = jedis.msetnx(key + "id4", "value4", key + "id5", "value5", key + "id6", "value6");
        System.out.println(msetnx);

        // 与setex功能类似，区别是超时时间单位为：毫秒
        String psexex = jedis.psetex(key + "psetex", 30000, "gouzi");
        System.out.println(psexex);
    }

    @Test
    public void testString5() {
        String key = "strings:testString5:";
        jedis.set(key + "name", "gouzi xxxxxx");

        // 在偏移量的基础上 拼接字符串
        Long setRange = jedis.setrange(key + "name", 6, "i love you");
        System.out.println(setRange);
    }

    @Test
    public void testString6() {
        String key = "strings:testString6:";
        jedis.set(key + "name", "gouzi");

        Long strlen = jedis.strlen(key + "name");
        System.out.println(strlen);
    }


    @Test
    public void testString7() {

        String key = "strings:testString7:";
        boolean setbit = jedis.setbit(key + "name", 7, "1");
        System.out.println(setbit);

        boolean setbit2 = jedis.setbit(key + "name", 7, "0");
        System.out.println(setbit2);

        String flag = jedis.get(key + "name");
        System.out.println("__" + flag + "__");
    }


    @Test
    public void testString8() {
        String key = "strings:testString8:";

        jedis.set(key + "name", "gouzi");
        jedis.set(key + "age", "24");

        List<String> values = jedis.mget(key + "name", key + "age");
        System.out.println(values);

        String age = jedis.getSet(key + "age", "26");
        System.out.println(age);

    }

    @Test
    public void testString9() {
        String key = "strings:testString9:";

        jedis.set(key + "num", "3.14");
        Double result = jedis.incrByFloat(key + "num", 3.14);
        System.out.println(result);

        System.out.println(jedis.get(key + "num"));
    }
}
