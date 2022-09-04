/*
 * Copyright 2022 Cgglyle
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cgglyle.common.service;

import org.springframework.data.redis.core.RedisCallback;

import java.util.Collection;
import java.util.Set;

/**
 * Redis 封装方法接口
 *
 * @author lyle
 * @since 2022/09/01
 */
public interface IRedisService<T> {

    /**
     * 创建一个数据
     *
     * @param key key
     * @param value value
     */
    void set(String key, T value);

    /**
     * 创建一个带过期时间的数据
     *
     * @param key key
     * @param value value
     * @param time 过期时间
     */
    void set(String key, T value, long time);

    /**
     * 根据key获取一个数据
     *
     * @param key key
     * @return 数据
     */
    T get(String key);

    /**
     * 根据key删除一个数据
     *
     * @param key key
     */
    void delete(String key);

    /**
     * 批量删除数据
     *
     * @param keys {@code Collection<String>}类型key数组
     */
    void delete(Collection<String> keys);

    /**
     * 给一个key设置一个到期时间
     *
     * @param key key
     * @param time 到期时间
     * @return 成功失败
     */
    boolean expire(String key, long time);

    /**
     * 获取一个key的到期时间
     *
     * @param key key
     * @return 到期时间
     */
    Long getExpire(String key);

    /**
     * 判断key是否存在
     *
     * @param key key
     * @return 存在为true，反之为false
     */
    boolean hasKey(String key);

    /**
     * key 的值递增
     *
     * @param key key
     * @param delta 递增长度
     * @return 当前长度
     */
    Long increment(String key, long delta);

    /**
     * key 的值递减
     *
     * @param key key
     * @param delta 递减长度
     * @return 当前长度
     */
    Long decrement(String key, long delta);

    /**
     * 添加一个set类型数据
     *
     * @param key key
     * @param value value
     */
    void addSet(String key, T value);

    /**
     * 获取一个set类型数据
     *
     * @param key key
     * @return 数据
     */
    Set<T> getSet(String key);

    /**
     * 删除一个set类型数据
     *
     * @param key key
     * @param value value
     */
    void deleteSet(String key, T value);

    /**
     * 在连接池中操作给定的对象
     *
     * @param redisCallback redis回调接口
     * @return T类型数据
     */
    T execute(RedisCallback<T> redisCallback);
}
