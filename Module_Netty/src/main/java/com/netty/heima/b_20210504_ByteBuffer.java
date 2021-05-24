package com.netty.heima;

import com.netty.ByteBufferUtil;

import java.nio.ByteBuffer;


public class b_20210504_ByteBuffer {

    public static void main(String[] args) {
//        ByteBuffer ByteBuffer1 = ByteBuffer.allocate(10);
//
//        //自动切换为读模式
//        ByteBuffer ByteBuffer2 = StandardCharsets.UTF_8.encode("hello");
//        ByteBufferUtil.debugAll(ByteBuffer2);

        //自动切换为读模式
        byte[] byteArray = "helloarray".getBytes();
        ByteBuffer byteBuffer3 = ByteBuffer.wrap(byteArray);
        ByteBufferUtil.debugAll(byteBuffer3);

        byteBuffer3.get();
        ByteBufferUtil.debugAll(byteBuffer3);
        byteBuffer3.put("new".getBytes());
        ByteBufferUtil.debugAll(byteBuffer3);

        byteBuffer3.compact();
        ByteBufferUtil.debugAll(byteBuffer3);

        byteBuffer3.clear();
        ByteBufferUtil.debugAll(byteBuffer3);
    }
}
