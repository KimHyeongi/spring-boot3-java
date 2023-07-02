package com.tistory.eclipse4j.domain.configuration;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;

import java.time.Duration;


@Configuration
@EnableCaching
public class LettuceRedisCacheCustomConfiguration {

	@SuppressWarnings("rawtypes ")
	@Bean
	public GenericObjectPoolConfig genericObjectPoolConfig() {
		GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
		genericObjectPoolConfig.setMaxIdle(maxIdle);
		genericObjectPoolConfig.setMinIdle(minIdle);
		genericObjectPoolConfig.setMaxTotal(maxActive);
		genericObjectPoolConfig.setMaxWait(Duration.ofMillis(maxWait));
		return genericObjectPoolConfig;
	}

	@Bean
	@Primary
	public RedisConnectionFactory redisConnectionFactory() {
		LettuceClientConfiguration lettuceClientConfiguration =
				LettucePoolingClientConfiguration.builder()
						.commandTimeout(Duration.ofMillis(timeout))
						.poolConfig(genericObjectPoolConfig())
						.build();
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(server, port);
		return new LettuceConnectionFactory(redisStandaloneConfiguration, lettuceClientConfiguration);
	}

	@Bean
	public RedisCacheConfiguration redisCacheConfiguration() {
		return RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(600)).disableCachingNullValues();
	}

	@Primary
	@Bean("redisCacheManager")
	public CacheManager redisCacheManager() {
		return RedisCacheManager.builder(redisConnectionFactory()).cacheDefaults(redisCacheConfiguration()).transactionAware().build();
	}


	@Bean("redisCacheManagerTenMin")
	public CacheManager redisCacheManagerTenMin() {
		return RedisCacheManager.builder(redisConnectionFactory())
				.cacheDefaults(RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(10)).disableCachingNullValues()).transactionAware()
				.build();
	}


	@Value("${spring.redis.host}")
	private String server;

	@Value("${spring.redis.port}")
	private int port;

	@Value("${spring.redis.timeout}")
	private long timeout;

	@Value("${spring.redis.lettuce.pool.max-idle:7}")
	private int maxIdle;

	@Value("${spring.redis.lettuce.pool.min-idle:2}")
	private int minIdle;

	@Value("${spring.redis.lettuce.pool.max-active:7}")
	private int maxActive;

	@Value("${spring.redis.lettuce.pool.max-wait:2000}")
	private long maxWait;
}
