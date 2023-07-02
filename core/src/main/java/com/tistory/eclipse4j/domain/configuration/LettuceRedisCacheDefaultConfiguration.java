package com.tistory.eclipse4j.domain.configuration;

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import java.time.Duration;
import java.util.Map;


//@Configuration
//@EnableCaching
public class LettuceRedisCacheDefaultConfiguration {


	@Bean
	public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
		return (builder) -> builder
				.withInitialCacheConfigurations(getCacheKeyValuesMap());
	}

	@Bean
	public RedisCacheConfiguration cacheConfiguration() {
		return RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(Duration.ofMinutes(60))
				.disableCachingNullValues()
				.serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
	}

	private Map<String, RedisCacheConfiguration> getCacheKeyValuesMap() {
		return Map.of("CacheData", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(600)).disableCachingNullValues());
	}
}
