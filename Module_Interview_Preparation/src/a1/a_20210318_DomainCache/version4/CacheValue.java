package a1.a_20210318_DomainCache.version4;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

class CacheValue<T> extends WeakReference<T> {

    private int hashcode;

    public CacheValue(T referent) {
        super(referent);
    }

    public CacheValue(T referent, ReferenceQueue q) {
        super(referent, q);
        this.hashcode = System.identityHashCode(referent);
    }

    @Override
    public int hashCode() {
        return this.hashcode;
    }

    @Override
    public boolean equals(Object obj) {
        T value;
        return (this == obj)
                || ( ((value = get()) != null ) && value.equals( ((CacheValue)obj).get() ) )
                ;
    }

}
