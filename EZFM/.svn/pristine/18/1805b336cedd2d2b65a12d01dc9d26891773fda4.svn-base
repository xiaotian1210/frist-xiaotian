package com.shareworx.platform.cache.support;

import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {
	
	@Autowired
	private Environment env;

	@Bean(name="jedisConnectionFactory")
	public JedisConnectionFactory redisConnectionFactory() {
		JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
		redisConnectionFactory.setDatabase(Integer.parseInt(env.getProperty("cache.redis.database", "0")));
		redisConnectionFactory.setPassword(env.getProperty("cache.redis.password", ""));
		redisConnectionFactory.setHostName(env.getProperty("cache.redis.host", ""));
		redisConnectionFactory.setPort(Integer.parseInt(env.getProperty("cache.redis.port", "6379")));
		return redisConnectionFactory;
	}
	
	@Bean(name="redisTempalte")
	public RedisTemplate<String, Object> redisTemplate(@Qualifier("jedisConnectionFactory") RedisConnectionFactory cf) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(cf);
		StringRedisSerializer serializer = new StringRedisSerializer(Charset.forName("UTF-8"));
		redisTemplate.setKeySerializer(serializer);
		redisTemplate.setHashKeySerializer(serializer);
		return redisTemplate;
	}
	
	@Bean(name="redisCacheManager")
	public CacheManager redisCacheManager(@Qualifier("redisTempalte") RedisTemplate<String, Object> redisTemplate) {
		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
		return cacheManager;
	}
}
