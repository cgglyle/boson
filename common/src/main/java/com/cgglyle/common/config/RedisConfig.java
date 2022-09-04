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

package com.cgglyle.common.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.Objects;

/**
 * Redis 配置
 *
 * @author lyle
 * @since 2022/09/01
 */
@Configuration
@EnableCaching
public class RedisConfig {

    /**
     * 序列化控制
     */
    @Bean
    RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        ObjectMapper objectMapper = new ObjectMapper();
        // 反序列化自动识别变量名
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        //反序列化识别对象类型
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
        //反序列化如果有多的属性，不抛出异常
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //反序列化如果碰到不识别的枚举值，是否作为空值解释，true:不会抛不识别的异常, 会赋空值，false:会抛不识别的异常
        objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        objectMapper.registerModule(JavaTimeModuleHandler.getTimeModule(javaTimeModule));
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer(objectMapper);
        // 设置值（value）的序列化采用GenericJackson2JsonRedisSerializer。
        redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer);
        // 设置键（key）的序列化采用StringRedisSerializer。
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate<String, Object> template) {
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofDays(1)) //Duration.ZERO表示永不过期（此值一般建议必须设置）
                .disableCachingNullValues() //不缓存null
                //.disableKeyPrefix() // 禁用key的前缀
                //=== 前缀我个人觉得是非常重要的，建议约定：注解缓存一个统一前缀、RedisTemplate直接操作的缓存一个统一前缀===
                //.prefixKeysWith("baidu:") // 底层其实调用的还是computePrefixWith() 方法，只是它的前缀是固定的（默认前缀是cacheName，此方法是把它固定住，一般不建议使用固定的）
                //.computePrefixWith(CacheKeyPrefix.simple()); // 使用内置的实现
                .computePrefixWith(cacheName -> "boson:" + cacheName) // 自己实现，建议这么使用(cacheName也保留下来了)
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(template.getStringSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(template.getValueSerializer()));

        return RedisCacheManager.RedisCacheManagerBuilder
                .fromConnectionFactory(Objects.requireNonNull(template.getConnectionFactory()))
                .cacheDefaults(configuration)
                .transactionAware().build();
    }
}
