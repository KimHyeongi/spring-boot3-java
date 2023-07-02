package com.tistory.eclipse4j.domain.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CaffeineCacheConfiguration {
    /**
     * https://programmer.group/caffeine-the-king-of-local-cache-performance.html
     * ex)
     * @Cacheable(cacheNames = "cachenames", cacheManager = "caffeineCacheManager")
     * public List<Object> getXxxxxxxxxx(Integer id) {
     */
    @Bean
    public CacheManager caffeineCacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("cached_redis_caffeine_company_id");
        cacheManager.setCaffeine(Caffeine.newBuilder()
                .initialCapacity(100)
                .maximumSize(500)
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .weakKeys()
                .recordStats());
        return cacheManager;
    }
}
