package com.globalsoftwaresupport.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Set;

@Service
public class RedisService {

    private final Jedis jedis;

    @Autowired
    public RedisService(Jedis jedis) {
        this.jedis = jedis;
    }

    public void setValue(String key, String value) {
        jedis.set(key, value);
    }

    public String getValue(String key) {
        return jedis.get(key);
    }

    public Set<String> getAllValues() {
        return jedis.keys("*");
    }



    public void deleteKey(String key) {
        jedis.del(key);
    }
}
