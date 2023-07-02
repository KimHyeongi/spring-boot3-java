package com.tistory.eclipse4j.domain.cache;

import lombok.Getter;

@Getter
public enum RedisCacheKeyProperties {

    cached_default(
            "시스템 유지 관리 캐시",
            60*60*24L,
            true,
            "KEY",
            0
    ),
    cached_dic_by_id(
            "사전 캐시",
            60 * 60 * 24L,
            true,
            "KEY",
            0
    );

    private final String description;
    private final long ttl;
    private final boolean key;
    private final String placeholder;
    private final int dpOrderNumber;

    RedisCacheKeyProperties(String description, long ttl, boolean key, String placeholder, int dpOrderNumber){
        this.description = description;
        this.ttl = ttl;
        this.key = key;
        this.placeholder = placeholder;
        this.dpOrderNumber = dpOrderNumber;
    }
}
