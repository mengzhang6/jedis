package morning.cat.pub;

import redis.clients.jedis.JedisPubSub;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2019/4/9 8:32 PM
 */
public class MyJedisPubSub extends JedisPubSub {

    @Override
    public void onMessage(String channel, String message) {
        System.out.println("onMessage " + channel + ":" + message);
    }

    @Override
    public void onPMessage(String pattern, String channel, String message) {
        System.out.println("onPMessage " + channel + ":" + message);
    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.println("onSubscribe " + channel + ":" + subscribedChannels);
    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        System.out.println("onUnsubscribe " + channel + ":" + subscribedChannels);
    }

    @Override
    public void onPUnsubscribe(String pattern, int subscribedChannels) {
        System.out.println("onPUnsubscribe " + pattern + ":" + subscribedChannels);
    }

    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
        System.out.println("onPSubscribe " + pattern + ":" + subscribedChannels);
    }

    @Override
    public void onPong(String pattern) {
        System.out.println("onPong " + pattern);

    }

}
