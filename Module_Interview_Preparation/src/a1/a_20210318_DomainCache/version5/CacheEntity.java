package a1.a_20210318_DomainCache.version5;


public abstract class CacheEntity<T> {

    private String key;

    private long expireTime;

    public abstract T initial();

    public CacheEntity() {
    }

    public CacheEntity(long expireTime) {
        this.expireTime = expireTime;
    }

    public CacheEntity(String key, long expireTime) {
        this.key = key;
        this.expireTime = expireTime;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public CacheEntity setExpireTime(long expireTime) {
        this.expireTime = expireTime;
        return this;
    }

    public String getKey() {
        return key;
    }

    public CacheEntity setKey(String key) {
        this.key = key;
        return this;
    }
}
