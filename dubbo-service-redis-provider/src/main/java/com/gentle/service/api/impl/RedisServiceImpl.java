package com.gentle.service.api.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.gentle.service.api.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description: redis 操作
 * @Author: Gentle
 * @date 2018/10/25 12:47
 */
@Service
public class RedisServiceImpl implements RedisService {

    private static final Integer DEFAULT_TIME = -1;
    private static final Long DEFAULT_DATA = 1L;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 不设置过期时间
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public boolean set(String key, String value) {
        return set(key, value, DEFAULT_TIME);
    }

    /**
     * 设置过期时间，可以传空值。避免并发环境下缓存穿透
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public boolean set(String key, String  value, int second) {
        try {

            if (second <= 0) {
                redisTemplate.opsForValue().set(key, value);
            } else {
                redisTemplate.opsForValue().set(key, value, second);
            }
            return true;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取相关键的值
     *
     * @param key
     * @return
     */
    @Override
    public String get(String key) {
        try {

            return redisTemplate.opsForValue().get(key);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return null;
    }
    @Override
    public Boolean del(String key) {
        try {
            return redisTemplate.delete(key);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 哈希存储，往哈希中设置相关属性，默认永久存在
     *
     * @param field
     * @param key
     * @param value
     */
    @Override
    public void hashSet(String key, String field, String value) {
        hashSet(key, field, value, DEFAULT_TIME);
    }

    /**
     * 哈希存储，往哈希中设置相关属性，自定义时间
     *
     * @param field
     * @param key
     * @param value
     * @param time
     */
    @Override
    public void hashSet(String key, String field, String value, long time) {


        try {
            if (time < 0) {
                redisTemplate.opsForHash().put(key, field, value);
            } else {
                redisTemplate.opsForHash().put(key, field, value);
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }


    }
    @Override
    public String hashGet(String key, String field) {

        try {
            return (String) redisTemplate.opsForHash().get(key, field);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 删除哈希中的属性
     *
     * @param field
     * @param key
     */
    @Override
    public void hashDel(String key, String field) {
        redisTemplate.opsForHash().delete(key, field);
    }

    /**
     * 判断key存不存在
     *
     * @param key
     * @return
     */
    @Override
    public Boolean hasKey(String key) {

        try {

            return redisTemplate.hasKey(key);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 自增，默认自增1
     *
     * @param key
     * @return
     */
    @Override
    public long incr(String key) {

        return incr(key, DEFAULT_DATA);
    }

    /**
     * 自增，自定义加多少
     *
     * @param key
     * @param delta
     * @return
     */
    @Override
    public Long incr(String key, long delta) {
        try {

            if (delta < 0) {
                throw new RuntimeException("递增因子必须大于0");
            }
            return redisTemplate.opsForValue().increment(key, delta);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 自减，自定义加多少
     *
     * @param key
     */
    @Override
    public long decr(String key) {

        return decr(key, DEFAULT_DATA);
    }

    /**
     * 自减，自定义减少数量
     *
     * @param key
     * @param delta
     * @return
     */
    @Override
    public Long decr(String key, long delta) {
        try {

            if (delta < 0) {
                throw new RuntimeException("递减因子必须大于0");
            }
            return redisTemplate.opsForValue().increment(key, -delta);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return null;
    }
    @Override
    public List<String> getMany(List<String> list) {

        if (list == null || list.isEmpty()) {
            return null;
        }
        try {
            return redisTemplate.opsForValue().multiGet(list);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return null;
    }


}
