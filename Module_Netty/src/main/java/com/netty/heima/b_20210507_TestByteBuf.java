package com.netty.heima;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;

import static io.netty.buffer.ByteBufUtil.appendPrettyHexDump;
import static io.netty.util.internal.StringUtil.NEWLINE;

public class b_20210507_TestByteBuf {

    public static void main(String[] args) {

        System.out.println(ByteBufAllocator.DEFAULT.heapBuffer());
        System.out.println(ByteBufAllocator.DEFAULT.directBuffer());

        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();

//        byteBuf.writeBoolean(true);
//        byteBuf.writeBoolean(false);
//        byteBuf.writeBytes("hello".getBytes());
//        log(byteBuf);
//
//        System.out.println(byteBuf.readInt());
//        log(byteBuf);
//
//        System.out.println(byteBuf.readByte());
//        log(byteBuf);


        byteBuf.writeBytes("helloworld".getBytes());
        ByteBuf slice1 = byteBuf.slice(0, 5);
        slice1.retain();
        ByteBuf slice2 = byteBuf.slice(5, 5);

//        byteBuf.release();
//        log(slice1);
//        log(slice2);
        log(byteBuf);
        System.out.println(ByteBufUtil.prettyHexDump(byteBuf));

    }

    private static void log(ByteBuf buffer) {
        int length = buffer.readableBytes();
        int rows = length / 16 + (length % 15 == 0 ? 0 : 1) + 4;
        StringBuilder buf = new StringBuilder(rows * 80 * 2)
                .append("read index:").append(buffer.readerIndex())
                .append(" write index:").append(buffer.writerIndex())
                .append(" capacity:").append(buffer.capacity())
                .append(NEWLINE);
        appendPrettyHexDump(buf, buffer);
        System.out.println(buf.toString());
    }

}
