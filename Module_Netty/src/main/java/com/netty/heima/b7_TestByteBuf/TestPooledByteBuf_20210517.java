package com.netty.heima.b7_TestByteBuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestPooledByteBuf_20210517 {

    private static final Logger log = LoggerFactory.getLogger(TestPooledByteBuf_20210517.class);

    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        int count = 100000000;
        ByteBuf buffer = null;
        ByteBuf buffer1 = ByteBufAllocator.DEFAULT.buffer();
        for (int i = 0; i < count; i++) {
            buffer = PooledByteBufAllocator.DEFAULT.buffer(10*1024);
            buffer.release();
            if( i % 1000000 == 0 )
                log.debug("execute {} times costs tine {}" , i , System.currentTimeMillis() - begin);
        }
        long end = System.currentTimeMillis();
        log.debug("execute {} times costs tine {}" , count , end - begin);
        //2021-05-17 16:49:39,487 [main] DEBUG c.n.h.b.TestPooledByteBuf_20210517 [TestPooledByteBuf_20210517.java : 23] - execute 100000000 times costs tine 12993

    }

}
