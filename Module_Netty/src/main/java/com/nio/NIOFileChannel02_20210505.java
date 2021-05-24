package com.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel02_20210505 {

    public static void main(String[] args) throws IOException {

        FileInputStream fileInputStream = new FileInputStream("./NIOFileChannel01_test.txt");

        FileChannel fileChannel = fileInputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(5);
        while( fileChannel.read(byteBuffer) != -1 ) {

            byteBuffer.flip();
            byte[] bytes = new byte[byteBuffer.limit()];
            byteBuffer.get(bytes);
            System.out.println(new String(bytes));
            //ByteBufferUtil.debugAll(byteBuffer);
            byteBuffer.clear();
        }
    }
}
