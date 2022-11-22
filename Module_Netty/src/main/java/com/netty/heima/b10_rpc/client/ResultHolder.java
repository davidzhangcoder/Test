package com.netty.heima.b10_rpc.client;

import io.netty.util.concurrent.Promise;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ResultHolder {

    private static ResultHolder resultHolder;

    private static final Map<Long, Promise<?>> RESULT_MAP = new ConcurrentHashMap<Long, Promise<?>>();

    private static AtomicLong atomicLong = new AtomicLong(1);

    private ResultHolder() {
    }

    public static ResultHolder getInstance() {
        if (resultHolder == null) {
            synchronized (ResultHolder.class) {
                if (resultHolder == null){
                    resultHolder = new ResultHolder();
                }
            }
        }

        return resultHolder;
    }

    public Promise<?> get(long seqid) {
        return RESULT_MAP.get(seqid);
    }

    public void put(long seqID, Promise promise){
        if (RESULT_MAP.get(seqID)==null) {
            RESULT_MAP.put(seqID,promise);
            return;
        }

        throw new RuntimeException("duplicate seqID is not allowed");
    }

    public long getSeqID(){
        return atomicLong.getAndIncrement();
    }
}
