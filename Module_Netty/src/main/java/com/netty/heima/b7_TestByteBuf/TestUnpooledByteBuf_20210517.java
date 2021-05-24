package com.netty.heima.b7_TestByteBuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestUnpooledByteBuf_20210517 {

    private static final Logger log = LoggerFactory.getLogger(TestUnpooledByteBuf_20210517.class);

    public static void main(String[] args) throws InterruptedException {
        long begin = System.currentTimeMillis();
        int count = 100000000;
        ByteBuf buffer = null;
        for (int i = 0; i < count; i++) {
            buffer = Unpooled.buffer(10*1024);
            buffer.release();

            Thread.sleep(100);
            if( i % 1000000 == 0 )
                log.debug("execute {} times costs tine {}" , i , System.currentTimeMillis() - begin);
        }
        long end = System.currentTimeMillis();
        log.debug("execute {} times costs tine {}" , count , end - begin);
//        2021-05-17 16:43:30,765 [main] DEBUG c.n.h.b.TestUnpooledByteBuf_20210517 [TestUnpooledByteBuf_20210517.java : 23] - execute 100000000 times costs tine 95217

    }

}
