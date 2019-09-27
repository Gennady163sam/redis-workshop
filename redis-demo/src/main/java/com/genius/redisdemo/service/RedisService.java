package com.genius.redisdemo.service;

import org.springframework.stereotype.Controller;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

@Controller
public class RedisService {
    private Jedis jedis = new Jedis();

    public void addValueStringByKey(String key, String value) {
        jedis.set(key, value);
    }

    public void addArrayByKey(String key, List<String> values) {
        //jedis.lpush(key, values.toArray(new String[0]));
        jedis.lpush(key, "testValue");
    }

    public void addElementToArray(String key, String value) {
        jedis.sadd(key, value);
    }

    public String value(String key) {
        return jedis.get(key);
    }

    public String list(String key) {
        return jedis.lpop(key);
    }

    public Set<String> set(String key) {
        return jedis.smembers(key);
    }
}
