本工程为使用jedis访问redis的样例工程


一、环境准备：

1.下载redis
https://redis.io/

2.在Linux机器上部署redis服务
解压
tar -xzvf *.tar.gz
编译
make
安装 
make PREFIX=/usr/local/redis install
将配置文件拷贝出来
cp redis.conf /usr/local/redis/

3.启动redis
./bin/redis-server ./redis.conf

4.jedis
Jedis 是 Redis 官方首选的 Java 客户端开发包
<dependency>
	<groupId>redis.clients</groupId>
	<artifactId>jedis</artifactId>
	<version>2.9.0</version>
</dependency>


二、拓展知识：

1、String类型常用操作
Strings 数据结构是简单的key-value类型，value其实不仅是String，也可以是数字，常用的命令有：
  ● set：新增或修改记录
  ● get：查看获取（打印）记录
  ● decr：原子递减（此时value为数字）
  ● incr：原子递增（此时value为数字）
  ● mset：批量写入
  ● mget：批量读取

2、hash类型常用操作
Redis的Hash实际是内部存储的Value为一个HashMap，并提供了直接存取这个Map成员的接口，常用的命令有：
  ● hset key field value：将哈希表 key 中的字段 field 的值设为 value
  ● hget key field：获取存储在哈希表中指定字段的值
  ● hdel key field2 [field2]：删除一个或多个哈希表字段
  ● hkeys key：获取所有哈希表中的字段
  ● hvals key：获取哈希表中所有值
  ● hexists key field：查看哈希表 key 中，指定的字段是否存在
  ● hgetall key：获取在哈希表中指定 key 的所有字段和值
  ● hlen key：获取哈希表中字段的数量
  ● hmset key field1 value1 [field2 value2 ]：同时将多个 field-value (域-值)对设置到哈希表 key 中
  ● hmget key field1 [field2]：获取所有给定字段的值

3、list类型常用操作
Redis list的实现为一个双向链表，即可以支持反向查找和遍历，更方便操作，不过带来了部分额外的内存开销，常用命令：
  ● lpop key：移出并获取列表的第一个元素
  ● lpush key value1 [value2]：将一个或多个值插入到列表头部
  ● lpushx key value：将值value插入到列表key的表头，当且仅当key存在并且是一个列表
  ● rpop key：移除并获取列表最后一个元素
  ● rpush key value1 [value2]：在列表中添加一个或多个值
  ● rpushx key value：为已存在的列表添加值
  ● lrange key start stop：获取列表指定范围内的元素，lrange key 0 -1 表示输出所有元素
  ● lrem key count value：根据参数count的值（count > 0:从表头向表尾搜索，数量为count，count < 0从表尾向表头搜索，数量为count的绝对值，count = 0 移除表中所有与value相等的值），移除列表中与参数value相等的元素
  ● lset key index value：通过索引设置列表元素的值
  ● lindex key index：通过索引获取列表中的元素
  ● llen key：获取列表长度

4、set类型常用操作
Set是string类型的无序集合。集合成员是唯一的。常用命令：
  ● sadd key member1 [member2]：向集合添加一个或多个成员
  ● sdiff key1 [key2]：返回给定所有集合的差集
  ● sdiffstore destination key1 [key2]：返回给定所有集合的差集并存储在 destination 中
  ● sinter key1 [key2]：返回给定所有集合的交集
  ● sinterstore destination key1 [key2]：返回给定所有集合的交集并存储在 destination 中
  ● sunion key1 [key2]：返回所有给定集合的并集
  ● sunionstore destination key1 [key2]：所有给定集合的并集存储在 destination 集合中
  ● smove source destination member：将 member 元素从 source 集合移动到 destination 集合
  ● spop key：移除并返回集合中的一个随机元素
  ● srandmember key [count]：返回集合中一个或多个随机数
  ● srem key member1 [member2]：移除集合中一个或多个成员
  ● smembers key：返回集合中的所有成员
  ● sismember key member：判断 member 元素是否是集合 key 的成员
  ● scard key：获取集合的成员数

5、Sorted Set类型常用操作
Sorted Set和Set一样也是string类型元素的集合,且不允许重复的成员。不同的是Sorted Set每个元素都会关联一个double类型的分数。redis正是通过分数来为集合中的成员进行从小到大的排序。常用命令：
  ● zadd key score1 member1 [score2 member2]：向有序集合添加一个或多个成员，或者更新已存在成员的分数
  ● zcard key：获取有序集合的成员数
  ● zcount key min max：计算在有序集合中指定区间分数的成员数
  ● zrange key start stop [withscores]：通过索引区间返回有序集合成指定区间内的成员
  ● zremrange key start stop [withscores]：返回有序集中指定区间内的成员，通过索引，分数从高到底
  ● zrangebyscore key min max [withscores] [limit]：通过分数返回有序集合指定区间内的成员
  ● zremrangebyscore key min max：移除有序集合中给定的分数区间的所有成员
  ● zrank key member：返回有序集合中指定成员的索引
  ● zrevrank key member：返回有序集合中指定成员的排名，有序集成员按分数值递减(从大到小)排序
  ● zrem key member [member ...]：移除有序集合中的一个或多个成员
  ● zremrangebyrank key start stop：移除有序集合中给定的排名区间的所有成员
  ● zrevrangebyscore key max min [withscores]：返回有序集中指定分数区间内的成员，分数从高到低排序
  ● zscore key member：返回有序集中，成员的分数值

6、Pub/Sub类型常用操作
Pub/Sub（发布/订阅）是一种消息通信模式：发送者(pub)发送消息，订阅者(sub)接收消息，可用于实时消息系统。常用命令：
  ● subscribe channel [channel ...]：订阅给定的一个或多个频道的信息。
  ● unsubscribe [channel [channel ...]]：指退订给定的频道。
  ● publish channel message：将信息发送到指定的频道。
  ● pubsub subcommand [argument [argument ...]]：查看订阅与发布系统状态。
