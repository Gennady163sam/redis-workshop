package com.genius.redisdemo.controller;

import org.springframework.stereotype.Controller;
import redis.clients.jedis.Jedis;

@Controller
public class RedisController {
    private Jedis jedis = new Jedis();

    public void addValueStringByKey(String key, String value) {
        jedis.set(key, value);
    }

    public String value(String key) {
        return jedis.get(key);
    }
}
