package com.weswu.gcpauth.base;

import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author weswu
 * @date 2021/5/24
 */
public class CacheBase {
    public ConcurrentHashMap<String, CachedObject> cacheStore = null;
    public  int capacity;
    public  int expireTime;

    public boolean put(String cacheKey, Object cached) throws GAException {
        if (!trimToCapcity()){
            String errMessage = "Cache is full when puting the key: " + cacheKey + ".";
            throw new GAException("Cache Error",errMessage);
        }
        Calendar timeout = Calendar.getInstance();
        timeout.setTimeInMillis(timeout.getTimeInMillis() + expireTime * 1000L);
        CachedObject cacheObject = new CachedObject(cached, timeout);
        cacheStore.put(cacheKey, cacheObject);
        return true;
    }

    public boolean put(Long cacheKey, Object cached) throws GAException{
        return put(Long.toString(cacheKey), cached);
    }

    public Object get(String cacheKey){
        if(this.has(cacheKey)){
            CachedObject cachedObject = cacheStore.get(cacheKey);
            if(cachedObject.expire != null){
                if(Calendar.getInstance().before(cachedObject.expire)){
                    return cachedObject.cachedObject;
                }else{
                    return null;
                }
            }
            return cachedObject.cachedObject;
        }
        return null;
    }

    public Object get(Long cacheKey){
        return get(Long.toString(cacheKey));
    }

    public boolean trimToCapcity(){
        boolean result = false;
        if (this.count() <= capacity || this.isEmpty()) {
            return true;
        }
        for (Map.Entry<String, CachedObject> toRemove : cacheStore.entrySet()) {
            CachedObject cachedObject = toRemove.getValue();
            if(cachedObject.expire != null){
                if(Calendar.getInstance().after(cachedObject.expire)){
                    this.delete(toRemove.getKey());
                }
            }
        }
        if (this.count() <= capacity){
            result = true;
        }
        return result;

    }

    public boolean has(String cacheKey){
        return cacheStore.containsKey(cacheKey);
    }

    public boolean has(long cacheKey){
        return has(Long.toString(cacheKey));
    }

    public boolean isEmpty(){
        return cacheStore.isEmpty();
    }

    public void delete(String cacheKey){
        cacheStore.remove(cacheKey);
    }

    public void delete (Long cacheKey){
        delete(Long.toString(cacheKey));
    }

    public int count(){
        return cacheStore.size();
    }

    public void clear(){
        cacheStore.clear();
    }
}
/* the object that will be cached */
class CachedObject{
    Calendar expire;
    Object cachedObject;
    public CachedObject(Object cachedObject){
        this.cachedObject = cachedObject;
    }
    public CachedObject(){
    }
    public CachedObject(Object cachedObject, Calendar expire){
        this.cachedObject = cachedObject;
        this.expire = expire;
    }
}
