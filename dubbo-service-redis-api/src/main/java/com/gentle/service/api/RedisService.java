package com.gentle.service.api;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Gentle
 * @date 2019/04/06 : 12/48
 */
public interface RedisService {

     boolean set(String key, String value);

    /**
     * 设置过期时间，可以传空值。避免并发环境下缓存穿透
     *
     * @param key redis key
     * @param value redis 值
     * @return
     */
     boolean set(String key, String  value, int second);
    /**
     * 获取相关键的值
     *
     * @param key redis key
     * @return
     */

     String get(String key);

    /**
     *  根据 key 删除值
     * @param key redis key
     * @return
     */
     Boolean del(String key);

    /**
     * 哈希存储，往哈希中设置相关属性，默认永久存在
     *
     * @param field 字段
     * @param key redis key
     * @param value redis 值
     */
     void hashSet(String key, String field, String value);

    /**
     * 哈希存储，往哈希中设置相关属性，自定义时间
     *
     * @param field 字段
     * @param key 键
     * @param value 值
     * @param time 时间（秒计算）
     */
     void hashSet(String key, String field, String value, long time);


    public String hashGet(String key, String field);

    /**
     * 删除哈希中的属性
     *
     * @param field 字段
     * @param key 键
     */
     void hashDel(String key, String field);

    /**
     * 判断key存不存在
     *
     * @param key 键
     * @return null 表示没有这个键
     */
     Boolean hasKey(String key);

    /**
     * 自增，默认自增1
     *
     * @param key 键
     * @return 1
     */
     long incr(String key);

    /**
     * 自增，自定义加多少
     *
     * @param key 键
     * @param delta 增加多少
     * @return
     */
     Long incr(String key, long delta);

    /**
     * 自减，自定义加多少
     *
     * @param key 键
     */
     long decr(String key);

    /**
     * 自减，自定义减少数量
     *
     * @param key
     * @param delta
     * @return
     */
    Long decr(String key, long delta);

    /**
     * 根据键拿多个值
     * @param list 键集合
     * @return 集合值
     */
     List<String> getMany(List<String> list);
}
