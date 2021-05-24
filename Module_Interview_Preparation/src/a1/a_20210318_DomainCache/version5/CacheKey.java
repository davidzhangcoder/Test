package a1.a_20210318_DomainCache.version5;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class CacheKey<T> extends WeakReference<T> {

    private int hashcode;

    public CacheKey(T referent) {
        super(referent);
    }

    public CacheKey(T referent, ReferenceQueue q) {
        super(referent, q);
        this.hashcode = System.identityHashCode(referent);
    }

    @Override
    public int hashCode() {
        return this.hashcode;
    }

    @Override
    public boolean equals(Object obj) {
        T key;
        return (this == obj)
                || ( ((key = get()) != null ) && key.equals( ((CacheKey)obj).get() ) )
                ;
    }
}
