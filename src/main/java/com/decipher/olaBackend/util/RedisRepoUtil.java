package com.decipher.olaBackend.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class RedisRepoUtil {

    private final RedisTemplate<String, Object> redisTemplate;
    private final StringRedisTemplate stringRedisTemplate;

    public RedisRepoUtil(RedisTemplate<String, Object> redisTemplate, StringRedisTemplate stringRedisTemplate) {
        this.redisTemplate = redisTemplate;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public void save(String key, Object value) {
        redisTemplate.opsForValue()
                .set(key, value);
    }
    public void save(String key, Object value, long ttl, TimeUnit timeUnit){
        redisTemplate.opsForValue().set(key,value,ttl,timeUnit);
    }

    public Object findByKey(String id) {
        return redisTemplate.opsForValue().get(id);
    }

    public boolean containsKey(String id) {
        return redisTemplate.hasKey(id);
    }



    public void deleteCache(String id) {
        if (containsKey(id)) {
            redisTemplate.delete(id);
        }
    }

    public Set<String> findKeys(String pattern){
        return redisTemplate.keys(pattern);
    }


    public long inc(String key) {
        return stringRedisTemplate.opsForValue().increment(key);
    }
}
