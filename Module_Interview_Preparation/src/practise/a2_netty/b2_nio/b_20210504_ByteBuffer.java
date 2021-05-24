package practise.a2_netty.b2_nio;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;


public class b_20210504_ByteBuffer {

    public static void main(String[] args) {
        ByteBuffer allocate = ByteBuffer.allocate(10);

        StandardCharsets.UTF_8.encode("aaa");

//        ByteBuffer hello = StandardCharsets.UTF_8.encode("hello");

    }
}
