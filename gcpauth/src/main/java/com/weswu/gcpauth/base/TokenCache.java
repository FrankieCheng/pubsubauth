package com.weswu.gcpauth.base;

import com.weswu.gcpauth.config.Configuration;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author weswu
 * @date 2021/5/24
 */
public class TokenCache extends CacheBase{
    private static TokenCache instance;

    private TokenCache(){
        cacheStore = new ConcurrentHashMap<String, CachedObject>(10, 0.9f, 1);
        capacity = 100;
        expireTime = Configuration.CACHED_TOKENS_TTL;
    }

    public static TokenCache getInstance(){
        if(instance == null){
            synchronized (TokenCache.class) {
                if(instance == null){
                    instance = new TokenCache();
                }
            }
        }
        return instance;
    }

}
