package com.genius.redisdemo.service;

import org.springframework.stereotype.Controller;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class RedisService {
    private Jedis jedis = new Jedis();

    public void addValueStringByKey(String key, String value) {
        jedis.set(key, value);
    }

    public void addArrayByKey(String key, List<String> values) {
        jedis.lpush(key, values.toArray(String[]::new));
    }

    public void addElementToArray(String key, String value) {
        jedis.sadd(key, value);
    }

    public void changeObjectAttribute(String key, String fieldKey, String fieldValue) {
        jedis.hset(key, fieldKey, fieldValue);
    }

    public void addValueInTransaction() {
        Transaction transaction = jedis.multi();
        // add some actions
        transaction.exec();
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

    public Map<String, String> object(String key) {
        return jedis.hgetAll(key);
    }
}
